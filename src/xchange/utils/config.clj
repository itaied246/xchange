(ns xchange.utils.config
  (:require [struct.core :as st]))

(def schema
  [[:db-url st/required st/string]
   [:port st/required st/integer-str]])

(defn create-config
  [env]
  (st/validate env schema))
