(ns xchange.utils.db
  (:require [clojure.java.jdbc :as j]))

(defn insert!
  [con table row]
  (j/insert! con table row {:identifiers #(.replace % \_ \-)
                            :entities    #(.replace % \- \_)}))
