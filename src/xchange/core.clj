(ns xchange.core
  (:require [xchange.utils.config :refer [create-config]]
            [environ.core :refer [env]]
            [com.stuartsierra.component :as component]
            [xchange.system :as system]))

(defn -main
  [& args]
  (let [[err conf] (create-config env)]
    (if (nil? err)
      (component/start-system (system/new-system conf))
      (println err))))
