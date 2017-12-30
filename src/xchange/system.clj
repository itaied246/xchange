(ns xchange.system
  (:require
    [com.stuartsierra.component :as component]
    [xchange.api.schema :as schema]
    [xchange.server :as server]))

(defn new-system
  []
  (merge (component/system-map)
         (server/new-server)
         (schema/new-schema-provider)))
