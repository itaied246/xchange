(ns xchange.api.resolvers.core
  (:require [xchange.api.resolvers.mutations.offer :as m.offer]
            [xchange.api.resolvers.mutations.request :as m.request]
            [xchange.api.resolvers.mutations.comment :as m.comment]
            [xchange.api.resolvers.mutations.user :as m.user]
            [xchange.api.resolvers.queries.user :as q.user]))

(defn create-resolvers
  [db]
  (let [id {:id "1"}
        ids [id]]
    {:query/user                   (fn [& _] id)
     :query/users                  (fn [& _] ids)
     :query/offer                  (fn [& _] id)
     :query/offers                 (fn [& _] ids)
     :query/request                (fn [& _] id)
     :query/requests               (fn [& _] ids)

     :mutation/create-user         (m.user/create-user db)

     :mutation/create-offer        (m.offer/create-offer db)
     :mutation/update-offer        (m.offer/update-offer db)
     :mutation/remove-offer        (m.offer/remove-offer db)

     :mutation/create-request      (m.request/create-request db)
     :mutation/update-request      (m.request/update-request db)
     :mutation/remove-request      (m.request/remove-request db)

     :mutation/add-request-comment (m.comment/add-request-comment db)
     :mutation/add-offer-comment   (m.comment/add-offer-comment db)
     :mutation/remove-comment      (m.comment/remove-comment db)
     :mutation/update-comment      (m.comment/update-comment db)}))
