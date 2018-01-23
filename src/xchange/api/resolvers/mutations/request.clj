(ns xchange.api.resolvers.mutations.request
  (:require [xchange.utils.validations :as v]
            [clojure.spec.alpha :as s]))

(s/def ::description (partial v/max-length 5000))
(s/def ::title (s/and v/not-empty? (partial v/max-length 100)))
(s/def ::id v/not-empty?)

(s/def ::request (s/keys :opt-un [::id ::description ::title]))

(defn create-request
  [db]
  (fn [context args value]
    (v/do-if-valid ::request args
                   :ok)))

(defn update-request
  [fb]
  (fn [context args value]
    (v/do-if-valid ::request args
                   :ok)))

(defn remove-request
  [db]
  (fn [context args value]
    (v/do-if-valid ::request args
                   :ok)))
