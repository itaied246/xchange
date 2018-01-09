(ns xchange.api.resolvers.mutations.user
  (:require [com.walmartlabs.lacinia.resolve :refer [resolve-as]]
            [xchange.api.schema-validations :as sv]))

(defn create-user
  [context args value]
  (let [[res err-msg] (sv/validate-args sv/user-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))
