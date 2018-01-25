(ns xchange.core
  (:require [xchange.utils.config :refer [create-config]]
            [environ.core :refer [env]]
            [com.stuartsierra.component :as component]
            [xchange.components.system :as system])
  (:gen-class))

(defn -main
  [& args]
  (try
    (let [conf (create-config env)]
      (component/start (system/new-system conf)))
    (catch Exception e
      (println e))))
