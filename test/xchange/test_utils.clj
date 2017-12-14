(ns xchange.test-utils
  (:require [user]))

(def stub-context
  {:query/user  (fn [& _] {:id "1"})
   :query/users (fn [& _] '({:id "1"}))})

(defn q
  [query-string]
  (user/q query-string stub-context))
