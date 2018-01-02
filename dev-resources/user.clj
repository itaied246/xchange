(ns user
  (:require
    [clojure.java.browse :refer [browse-url]]
    [xchange.system :as system]
    [com.stuartsierra.component :as component]))

(defonce system (system/new-system {}))

(defn start
  []
  (alter-var-root #'system component/start-system)
  (browse-url "http://localhost:8888/")
  :started)

(defn stop
  []
  (alter-var-root #'system component/stop-system)
  :stopped)
