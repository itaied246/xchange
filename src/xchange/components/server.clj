(ns xchange.components.server
  (:require [com.stuartsierra.component :as component]
            [com.walmartlabs.lacinia.pedestal :as lp]
            [io.pedestal.http :as http]))

(defrecord Server [port schema server]

  component/Lifecycle

  (start [this]
    (assoc this :server (-> schema
                            :schema
                            (lp/service-map {:graphiql true})
                            (merge {::http/port port})
                            http/create-server
                            http/start)))

  (stop [this]
    (http/stop server)
    (assoc this :server nil)))

(defn new-server
  [port]
  (map->Server {:port port}))
