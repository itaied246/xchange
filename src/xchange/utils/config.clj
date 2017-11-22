(ns xchange.utils.config)

(def environment :environment)
(def db-url :db-url)

(defn create-config
  [e]
  {environment (e environment "development")})
