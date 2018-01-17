(ns xchange.api.resolvers.mutations.offer
  (:require [com.walmartlabs.lacinia.resolve :refer [resolve-as]]
            [xchange.api.schema-validations :as sv]
            [clojure.spec.alpha :as s]
            [xchange.utils.validations :as v]))

(s/def ::price #(< 1 % 999))
(s/def ::description (partial v/max-length 5000))
(s/def ::title (s/and (comp not empty?) (partial v/max-length 100)))
(s/def ::id (comp not empty?))

(s/def ::offer (s/keys :opt-un [::price ::description ::title ::id]))

(defn create-offer
  [context args value]
  (v/do-if-valid ::offer args
                 :ok))

(defn update-offer
  [context args value]
  (v/do-if-valid ::offer args
                 :ok))

(defn remove-offer
  [context args value]
  (v/do-if-valid ::offer args
                 :ok))
