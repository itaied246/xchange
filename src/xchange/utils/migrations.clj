(ns xchange.utils.migrations
  (:require [migratus.core :as migratus]
            [environ.core :refer [env]]
            [xchange.utils.config :refer [create-config-spec]]))

(def config
  (try
    (let [conf (create-config env)
          db-url (:db-url conf)]
      {:store         :database
       :migration-dir "migrations"
       :db            db-url})
    (catch Exception e
      (println e))))

(defn create
  [description]
  (migratus/create config description))

(defn migrate
  []
  (migratus/migrate config))

(defn rollback
  []
  (migratus/rollback config))

(defn reset
  []
  (migratus/reset config))
