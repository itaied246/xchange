(ns xchange.api.resolvers.mutations.comment
  (:require [xchange.utils.validations :as v]
            [clojure.spec.alpha :as s]))

(s/def ::body (s/and v/not-empty? (partial v/max-length 5000)))
(s/def ::offer-id v/not-empty?)
(s/def ::id v/not-empty?)

(s/def ::comment (s/keys :opt-un [::id ::offer-id ::body]))

(defn remove-comment
  [context args value]
  (v/do-if-valid ::comment args
                 :ok))

(defn update-comment
  [context args values]
  (v/do-if-valid ::comment args
                 :ok))

(defn add-offer-comment
  [context args value]
  (v/do-if-valid ::comment args
                 :ok))

(defn add-request-comment
  [context args value]
  (v/do-if-valid ::comment args
                 :ok))