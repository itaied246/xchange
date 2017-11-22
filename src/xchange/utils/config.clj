(ns xchange.utils.config
  (:require [bouncer.core :as b]
            [bouncer.validators :as v]))

(def db-url :db-url)

(defn create-config
  [e]
  (let [c {db-url (e :db-url)}]
    (b/validate c
                db-url v/required)))
