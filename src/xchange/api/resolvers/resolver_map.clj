(ns xchange.api.resolvers.resolver-map
  (:require [xchange.api.resolvers.mutations.offer :as m.offer]
            [xchange.api.resolvers.mutations.comment :as m.comment]))

(defn resolver-map
  [component]
  (let [id {:id "1"}
        ids [id]]
    {:query/user                 (fn [& _] id)
     :query/users                (fn [& _] ids)
     :query/offer                (fn [& _] id)
     :query/offers               (fn [& _] ids)
     :query/request              (fn [& _] id)
     :query/requests             (fn [& _] ids)

     :mutation/create-user       (fn [& _] id)
     :mutation/create-offer      m.offer/create-offer
     :mutation/add-offer-comment m.offer/add-offer-comment
     :mutation/update-offer      m.offer/update-offer
     :mutation/remove-offer      m.offer/remove-offer

     :mutation/remove-comment    m.comment/remove-comment
     :mutation/update-comment    m.comment/update-comment}))
