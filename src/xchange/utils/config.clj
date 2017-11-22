(ns xchange.utils.config
  (:require [bouncer.core :as b]
            [bouncer.validators :as v]))

(def db-url :db-url)

(defn create-config
  [e]
  (b/validate e
              db-url [v/required v/string]))
