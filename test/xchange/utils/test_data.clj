(ns xchange.utils.test-data
  (:require [migratus.core :as migratus]
            [environ.core :refer [env]]
            [xchange.utils.config :refer [create-config]]))

(def config
  (try
    (let [conf (create-config env)
          db-url (:db-url conf)]
      {:store         :database
       :migration-dir "test_data"
       :db            db-url})
    (catch Exception e
      (println e))))

(defn create
  [description]
  (migratus/create config description))

(defn up
  [& ids]
  (apply migratus/up config ids))

(defn down
  [& ids]
  (apply migratus/down config ids))
