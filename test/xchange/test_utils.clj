(ns xchange.test-utils
  (:require [clojure.test :refer [is]]
            [com.stuartsierra.component :as component]
            [clojure.walk :as walk]
            [com.walmartlabs.lacinia :refer [execute]]
            [xchange.api.schema :refer [new-schema load-schema]]
            [clojure.test :as t]
            [clojure.string :as str]
            [xchange.utils.config :refer [create-config]]
            [environ.core :refer [env]]
            [xchange.components.system :as system])
  (:import (clojure.lang IPersistentMap)))

(defn set= [& vectors] (apply = (map set vectors)))

(defn stub-resolvers
  [schema]
  (zipmap
    (keys schema)
    (repeat '(fn [& _] nil))))

(defn simplify
  "Converts all ordered maps nested within the map into standard hash maps, and
   sequences into vectors, which makes for easier constants in the tests, and eliminates ordering problems."
  [m]
  (walk/postwalk
    (fn [node]
      (cond
        (instance? IPersistentMap node)
        (into {} node)

        (seq? node)
        (vec node)

        :else
        node))
    m))


(def schema (->> (load-schema nil)
                 stub-resolvers))

(defn q
  [query-string]
  (-> schema
      (execute query-string nil nil)
      simplify))

(defmacro valid?
  [query]
  `(let [res# (q ~query)
         err# (->> res# :errors first)]
     (is (nil? err#))))

(defmacro invalid-args?
  [args query]
  `(let [res# (q ~query)
         err-msg# (-> res# :errors first (:message "{}"))
         bad-args# (->> err-msg# read-string keys)]
     (is (set= bad-args# ~args))))

(defmacro missing-args?
  [args query]
  `(let [res# (q ~query)
         err# (->> res# :errors first)]
     (is (set= (:missing-arguments err#) ~args))))

(defmacro defspec-test
  ([name sym-or-syms] `(defspec-test ~name ~sym-or-syms nil))
  ([name sym-or-syms opts]
   (when t/*load-tests*
     `(def ~(vary-meta name assoc :test `(fn []
                                           (let [check-results# (clojure.spec.test.alpha/check ~sym-or-syms ~opts)
                                                 checks-passed?# (every? nil? (map :failure check-results#))]
                                             (if checks-passed?#
                                               (t/do-report {:type    :pass
                                                             :message (str "Generative tests pass for "
                                                                           (str/join ", " (map :sym check-results#)))})
                                               (doseq [failed-check# (filter :failure check-results#)
                                                       :let [r# (clojure.spec.test.alpha/abbrev-result failed-check#)
                                                             failure# (:failure r#)]]
                                                 (t/do-report
                                                   {:type     :fail
                                                    :message  (with-out-str (clojure.spec.alpha/explain-out failure#))
                                                    :expected (->> r# :spec rest (apply hash-map) :ret)
                                                    :actual   (if (instance? Throwable failure#)
                                                                failure#
                                                                (:clojure.spec.test/val failure#))})))
                                             checks-passed?#)))
        (fn [] (t/test-var (var ~name)))))))