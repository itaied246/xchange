(ns xchange.utils.migrations
  (:require [migratus.core :as migratus]
            [environ.core :refer [env]]
            [xchange.utils.config :refer [create-config]]))

(def config
  (let [[err conf] (create-config env)
        db-url (:db-url conf)]
    (if (nil? err)
      {:store         :database
       :migration-dir "migrations"
       :db            db-url}
      (println err))))

(defn create
  [f]
  (migratus/create config f))

(defn migrate
  []
  (migratus/migrate config))

(defn rollback
  []
  (migratus/rollback config))

(defn reset
  []
  (migratus/reset config))
