(ns xchange.components.system
  (:require
    [com.stuartsierra.component :as component]
    [xchange.components.schema :as schema]
    [xchange.components.server :as server]
    [xchange.components.db :as db]
    [xchange.components.resolvers :as resolvers]))

(defn new-system [{:keys [port db-url]}]
  (component/system-map

    :db (db/new-db db-url)

    :resolvers (component/using
                 (resolvers/new-resolvers)
                 [:db])

    :schema (component/using
              (schema/new-schema)
              [:resolvers])

    :server (component/using
              (server/new-server port)
              [:schema])))
