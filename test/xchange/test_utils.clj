(ns xchange.test-utils
  (:require [clojure.test :refer [is]]
            [com.stuartsierra.component :as component]
            [clojure.walk :as walk]
            [com.walmartlabs.lacinia :refer [execute]]
            [xchange.api.schema :refer [new-schema]])
  (:import (clojure.lang IPersistentMap)))

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

(def schema (-> (new-schema)
                component/start
                :schema))

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
         err# (->> res# :errors first)
         bad-args# (->> err# :message read-string keys)]
     (is (not (nil? err#)))
     (is (= ~args bad-args#))))

(defmacro missing-args?
  [args query]
  `(let [res# (q ~query)
         err# (->> res# :errors first)]
     (is (not (nil? err#)))
     (is (= ~args (:missing-arguments err#)))))