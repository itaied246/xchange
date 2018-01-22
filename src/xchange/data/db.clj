(ns xchange.data.db
  (:require [com.stuartsierra.component :as component]))

(defrecord Database [db-url db]

  component/Lifecycle

  (start [this]
    (assoc this :db nil))

  (stop [this]
    (assoc this :db nil)))

(defn new-db
  [db-url]
  (map->Database {:db-url db-url}))
