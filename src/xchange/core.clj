(ns xchange.core
  (:require [xchange.utils.config :refer [create-config]]
            [environ.core :refer [env]]
            [clojure.java.jdbc :as jdbc]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [[e c] (create-config env)
        db-url (c :db-url)]
    (print (jdbc/query db-url
                ["select 3*5 as result"]))))
