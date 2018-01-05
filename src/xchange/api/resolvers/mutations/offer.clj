(ns xchange.api.resolvers.mutations.offer
  (:require [struct.core :as st]
            [com.walmartlabs.lacinia.resolve :refer [resolve-as]]))

(def offer-input-schema
  {:price       [st/positive [st/in-range 1 999]]
   :description [[st/max-count 5000]]
   :title       [[st/max-count 100]]})

(def comment-input-schema
  {:body [[st/max-count 5000]]})

(defn- validate-args
  [schema args]
  (let [err-msg (-> args (st/validate schema) first str)]
    (if (empty? err-msg)
      [true nil]
      [false {:message err-msg}])))

(defn create-offer
  [context args value]
  (let [[res err-msg] (validate-args offer-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))

(defn update-offer
  [context args value]
  (let [[res err-msg] (validate-args offer-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))

(defn add-offer-comment
  [context args value]
  (let [[res err-msg] (validate-args comment-input-schema args)]
    (if-not res
      (resolve-as nil err-msg))))
