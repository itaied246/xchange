(ns xchange.models.user
  (:require [clojure.spec.alpha :as s]
            [xchange.utils.validations :as v]))

(def phone-regex #"\d{10}")
(def email-regex #"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$")

(s/def ::phone (s/and string? (partial re-matches phone-regex)))
(s/def ::email (s/and string? (partial re-matches email-regex)))
(s/def ::name (s/and string? (partial v/length-in-range 1 50)))

(s/def ::user (s/keys :req-un [::name] :opt-un [::email ::phone]))