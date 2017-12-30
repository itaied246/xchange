(ns xchange.utils.config
  (:require [bouncer.core :as b]
            [bouncer.validators :as v]))

(defn create-config
  [conf]
  (-> {:db-url (:db-url conf)
       :port   (Integer/parseInt (:port conf))}
      (b/validate :db-url [v/required v/string]
                  :port [v/required v/number])))
