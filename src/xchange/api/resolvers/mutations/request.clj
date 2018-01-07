(ns xchange.api.resolvers.mutations.request
  (:require [com.walmartlabs.lacinia.resolve :refer [resolve-as]]
            [xchange.api.schema-validations :as sv]))

(defn create-request
  [context args value]
  (let [[res err-msg] (sv/validate-args sv/request-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))

(defn update-request
  [context args value]
  (let [[res err-msg] (sv/validate-args sv/request-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))

(defn remove-request
  [context args value])

(defn add-request-comment
  [context args value]
  (let [[res err-msg] (sv/validate-args sv/comment-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))
