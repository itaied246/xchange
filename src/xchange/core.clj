(ns xchange.core
  (:require [xchange.utils.config :refer [create-config]]
            [environ.core :refer [env]]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [[e c] (create-config env)]
    (println (:db-url c))))
