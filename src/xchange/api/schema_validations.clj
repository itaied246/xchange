(ns xchange.api.schema-validations
  (:require [struct.core :as st]))

(def comment-input-schema
  {:body [[st/max-count 5000]]})

(def offer-input-schema
  {:price       [st/positive [st/in-range 1 999]]
   :description [[st/max-count 5000]]
   :title       [[st/max-count 100]]})

(defn validate-args
  [schema args]
  (let [err-msg (-> args (st/validate schema) first str)]
    (if (empty? err-msg)
      [true nil]
      [false {:message err-msg}])))
