(ns xchange.system
  (:require
    [com.stuartsierra.component :as component]
    [xchange.api.schema :as schema]
    [xchange.server.server :as server]))

(defn new-system
  [config]
  (let [{:keys [port]} config]
    (component/system-map
      :schema (schema/new-schema)
      :server (component/using
                (server/new-server port)
                [:schema]))))
