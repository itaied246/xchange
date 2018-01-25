(ns xchange.components.resolvers
  (:require [com.stuartsierra.component :as component]
            [xchange.api.resolvers.core :as resolvers]))

(defrecord Resolvers [db resolvers]

  component/Lifecycle

  (start [this]
    (assoc this :resolvers (resolvers/create-resolvers (:db db))))

  (stop [this]
    (assoc this :resolvers nil)))

(defn new-resolvers []
  (map->Resolvers {}))
