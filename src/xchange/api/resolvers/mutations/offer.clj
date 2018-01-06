(ns xchange.api.resolvers.mutations.offer
  (:require [struct.core :as st]
            [com.walmartlabs.lacinia.resolve :refer [resolve-as]]
            [xchange.api.schema-validations :as sv]))

(defn create-offer
  [context args value]
  (let [[res err-msg] (sv/validate-args sv/offer-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))

(defn update-offer
  [context args value]
  (let [[res err-msg] (sv/validate-args sv/offer-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))

(defn remove-offer
  [context args value])

(defn add-offer-comment
  [context args value]
  (let [[res err-msg] (sv/validate-args sv/comment-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))
