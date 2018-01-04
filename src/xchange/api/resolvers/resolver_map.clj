(ns xchange.api.resolvers.resolver-map
  (:require [xchange.api.resolvers.mutation.create-offer :refer [create-offer]]))

(defn resolver-map
  [component]
  (let [id {:id "1"}
        ids [id]]
    {:query/user            (fn [& _] id)
     :query/users           (fn [& _] ids)
     :query/offer           (fn [& _] id)
     :query/offers          (fn [& _] ids)
     :query/request         (fn [& _] id)
     :query/requests        (fn [& _] ids)

     :mutation/create-user  (fn [& _] id)
     :mutation/create-offer create-offer}))
