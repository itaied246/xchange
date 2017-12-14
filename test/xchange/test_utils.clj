(ns xchange.test-utils
  (:require [user]))

(def stub-context
  {:query/user (fn [] {:id "1"})})

(defn q
  [query-string]
  (user/q query-string stub-context))

