(ns xchange.api.resolvers.mutations.request
  (:require [com.walmartlabs.lacinia.resolve :refer [resolve-as]]
            [xchange.api.schema-validations :as sv]
            [xchange.utils.validations :as v]
            [clojure.spec.alpha :as s]))

(s/def ::description (partial v/max-length 5000))
(s/def ::title (s/and (comp not empty?) (partial v/max-length 100)))
(s/def ::id (comp not empty?))

(s/def ::request (s/keys :opt-un [::id ::description ::title]))

(defn create-request
  [context args value]
  (v/do-if-valid ::request args
                 :ok))

(defn update-request
  [context args value]
  (v/do-if-valid ::request args
                 :ok))

(defn remove-request
  [context args value]
  (v/do-if-valid ::request args
                 :ok))
