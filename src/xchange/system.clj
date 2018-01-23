(ns xchange.system
  (:require
    [com.stuartsierra.component :as component]
    [xchange.api.schema :as schema]
    [xchange.server.server :as server]
    [xchange.data.db :as db]))

(defn new-system
  [config]
  (let [{:keys [port db-url]} config]
    (component/system-map
      :schema (component/using
                (schema/new-schema)
                [:db])
      :db (db/new-db db-url)
      :server (component/using
                (server/new-server port)
                [:schema]))))
