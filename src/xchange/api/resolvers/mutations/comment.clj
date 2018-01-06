(ns xchange.api.resolvers.mutations.comment
  (:require [struct.core :as st]
            [com.walmartlabs.lacinia.resolve :refer [resolve-as]]
            [xchange.api.schema-validations :as sv]))

(defn remove-comment
  [context args value])

(defn update-comment
  [context args values]
  (let [[res err-msg] (sv/validate-args sv/comment-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))
