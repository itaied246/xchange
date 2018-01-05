(ns xchange.api.resolvers.mutation.create-offer
  (:require [struct.core :as st]
            [com.walmartlabs.lacinia.resolve :refer [resolve-as]]))

(def schema
  {:price       [st/positive [st/in-range 1 999]]
   :description [[st/max-count 5000]]})

(defn create-offer
  [context args value]
  (let [[err _] (st/validate args schema)
        err-msg (str err)]
    (if (not (nil? err))
      (resolve-as nil {:message err-msg}))))
