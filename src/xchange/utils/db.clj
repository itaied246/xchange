(ns xchange.utils.db
  (:require [clojure.java.jdbc :as j]))

(defn insert!
  [db table row]
  (j/insert! db table row {:identifiers #(.replace % \_ \-)
                           :entities    #(.replace % \- \_)}))

(defn query
  [db sql]
  (j/query db sql))