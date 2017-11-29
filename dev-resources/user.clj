(ns user
  (:require
    [xchange.api.schema :refer [load-schema]]
    [com.walmartlabs.lacinia :refer [execute]]))

(def schema (load-schema))

(defn q
  [query-string]
  (execute schema query-string nil nil))
