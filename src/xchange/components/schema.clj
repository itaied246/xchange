(ns xchange.components.schema
  (:require [com.stuartsierra.component :as component]
            [xchange.api.schema :as schema]))

(defrecord Schema [resolvers schema]

  component/Lifecycle

  (start [this]
    (assoc this :schema (schema/load-schema resolvers)))

  (stop [this]
    (assoc this :schema nil)))

(defn new-schema []
  (map->Schema {}))
