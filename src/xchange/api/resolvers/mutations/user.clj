(ns xchange.api.resolvers.mutations.user
  (:require [com.walmartlabs.lacinia.resolve :refer [resolve-as]]
            [clojure.spec.alpha :as s]
            [xchange.utils.validations :as v]))

(def phone-regex #"\d{10}")
(def email-regex #"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$")

(s/def ::phone (s/and string? (partial re-matches phone-regex)))
(s/def ::email (s/and string? (partial re-matches email-regex)))
(s/def ::name (s/and string? (comp not empty?) (partial v/max-length 50)))

(defmacro do-if-valid
  [spec val body]
  `(let [conform# (s/conform ~spec ~val)]
     (if (s/invalid? conform#)
       (resolve-as nil {:message (s/explain-str ~spec ~val)})
       ~body)))

(s/def ::create-user-input (s/keys :req-un [::name] :opt-un [::phone ::email]))
(defn create-user
  [context args value]
  (do-if-valid ::create-user-input args
               "ok"))
