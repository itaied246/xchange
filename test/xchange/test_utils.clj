(ns xchange.test-utils
  (:require [user]
            [xchange.api.schema :refer [load-schema]]))

(def stub-resolvers
  {:query/user  (fn [& _] {:id "1"})
   :query/users (fn [& _] '({:id "1"}))
   :query/offer (fn [& _] {:id "1"})})

(def schema (load-schema stub-resolvers))

(defn q
  [query-string]
  (user/q schema query-string))
