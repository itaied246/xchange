(ns xchange.test-utils
  (:require [user]
            [clojure.test :refer [is]]
            [xchange.api.schema :refer [load-schema]]))

(def stub-resolvers
  (let [id {:id "1"}
        ids [id]]
    {:query/user           (fn [& _] id)
     :query/users          (fn [& _] ids)
     :query/offer          (fn [& _] id)
     :query/offers         (fn [& _] ids)
     :query/request        (fn [& _] id)
     :query/requests       (fn [& _] ids)
     :mutation/create-user (fn [& _] id)}))

(def schema (load-schema stub-resolvers))

(defn q
  [query-string]
  (user/q schema query-string))

(defmacro valid?
  [query]
  `(let [res# (q ~query)
         err# (->> res# :errors first)]
     (is (nil? err#))))

(defmacro missing-args?
  [args query]
  `(let [res# (q ~query)
         err# (->> res# :errors first)]
     (is (not (nil? err#)))
     (is (= ~args (:missing-arguments err#)))))
