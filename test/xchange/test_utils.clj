(ns xchange.test-utils
  (:require [user]
            [clojure.test :refer [is]]
            [xchange.api.schema :refer [load-schema]]))

(def stub-resolvers
  {:query/user   (fn [& _] {:id "1"})
   :query/users  (fn [& _] '({:id "1"}))
   :query/offer  (fn [& _] {:id "1"})
   :query/offers (fn [& _] '({:id "1"}))})

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
  [query args]
  `(let [res# (q ~query)
         err# (->> res# :errors first)]
     (is (not (nil? err#)))
     (is (= ~args (:missing-arguments err#)))))
