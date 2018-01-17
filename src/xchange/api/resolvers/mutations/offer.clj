(ns xchange.api.resolvers.mutations.offer
  (:require [com.walmartlabs.lacinia.resolve :refer [resolve-as]]
            [xchange.api.schema-validations :as sv]
            [clojure.spec.alpha :as s]
            [xchange.utils.validations :as v]))

(s/def ::price (s/and int? #(< 1 % 999)))
(s/def ::description (s/and string? (partial v/max-length 5000)))
(s/def ::title (s/and string? (comp not empty?) (partial v/max-length 100)))

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
