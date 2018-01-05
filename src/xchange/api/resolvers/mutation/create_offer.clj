(ns xchange.api.resolvers.mutation.create-offer
  (:require [struct.core :as st]
            [com.walmartlabs.lacinia.resolve :refer [resolve-as]]))

(def schema
  {:price       [st/positive [st/in-range 1 999]]
   :description [[st/max-count 5000]]
   :title       [[st/max-count 100]]})

(defn create-offer
  [context args value]
  (let [err-msg (-> args (st/validate schema) first str)]
    (if-not (empty? err-msg)
      (resolve-as nil {:message err-msg}))))
