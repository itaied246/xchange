(ns xchange.api.resolvers.mutations.comment
  (:require [struct.core :as st]
            [com.walmartlabs.lacinia.resolve :refer [resolve-as]]
            [xchange.api.schema-validations :as sv]
            [xchange.utils.validations :as v]
            [clojure.spec.alpha :as s]))

(s/def ::body (s/and string? (comp not empty?) (partial v/max-length 5000)))
(s/def ::offer-id (s/and string? (comp not empty?)))
(s/def ::id (s/and string? (comp not empty?)))

(defn remove-comment
  [context args value])

(defn update-comment
  [context args values]
  (let [[res err-msg] (sv/validate-args sv/comment-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))

(defn add-offer-comment
  [context args value]
  (let [[res err-msg] (sv/validate-args sv/comment-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))
