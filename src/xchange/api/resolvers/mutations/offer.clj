(ns xchange.api.resolvers.mutations.offer
  (:require [clojure.spec.alpha :as s]
            [xchange.utils.validations :as v]
            [xchange.data.offer :as o]))

(s/def ::price #(< 1 % 999))
(s/def ::description (partial v/max-length 5000))
(s/def ::title (s/and v/not-empty? (partial v/max-length 100)))
(s/def ::id v/not-empty?)

(s/def ::offer (s/keys :opt-un [::price ::description ::title ::id]))

(defn- convert-offer
  [offer]
  (update offer :platform name))

(defn create-offer
  [db]
  (fn [context args value]
    (v/do-if-valid ::offer args
                   (->> args
                        convert-offer
                        (o/create-offer db)
                        first))))

(defn update-offer
  [db]
  (fn [context args value]
    (v/do-if-valid ::offer args
                   :ok)))

(defn remove-offer
  [db]
  (fn [context args value]
    (v/do-if-valid ::offer args
                   :ok)))
