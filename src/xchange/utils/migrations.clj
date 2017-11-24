(ns xchange.utils.migrations
  (:require [migratus.core :as migratus]
            [environ.core :refer [env]]
            [xchange.utils.config :refer [create-config]]))

; TODO handle config error

(def config
  (let [[e c] (create-config env)]
    {:store         :database
     :migration-dir "migrations"
     :db            (c :db-url)}))

(defn migrate
  []
  (migratus/migrate config))

(defn rollback
  []
  (migratus/rollback config))
