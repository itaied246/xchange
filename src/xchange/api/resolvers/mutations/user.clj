(ns xchange.api.resolvers.mutations.user
  (:require [clojure.spec.alpha :as s]
            [xchange.utils.validations :as v]
            [xchange.data.user :as u]))

(def phone-regex #"\d{10}")
(def email-regex #"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$")

(s/def ::phone (partial re-matches phone-regex))
(s/def ::email (partial re-matches email-regex))
(s/def ::name (s/and v/not-empty? (partial v/max-length 50)))

(s/def ::user (s/keys :opt-un [::name ::phone ::email]))

(defn create-user
  [db]
  (fn [context args value]
    (v/do-if-valid ::user args
                   (first (u/create-user db args)))))
