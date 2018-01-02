(ns xchange.system
  (:require
    [com.stuartsierra.component :as component]
    [xchange.api.schema :as schema]
    [xchange.server :as server]))

(defn new-system
  [config]
  (let []
    (merge (component/system-map)
           (server/new-server (:port config))
           (schema/new-schema-provider))))
