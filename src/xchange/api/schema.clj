(ns xchange.api.schema
  (:require [clojure.java.io :as io]
            [com.walmartlabs.lacinia.util :as util]
            [com.walmartlabs.lacinia.schema :as schema]
            [clojure.edn :as edn]))

(defn- type-file
  []
  [[:objects "schema/objects.edn"]
   [:enums "schema/enums.edn"]
   [:queries "schema/queries.edn"]
   [:interfaces "schema/interfaces.edn"]])

(defn- resolver-map
  []
  {:query/user  (fn [context args value]
                  ((:query/user context)))
   :query/users (fn [context args value]
                  ((:query/users context)))
   :query/offer (fn [context args value]
                  ((:query/offer context)))})

(defn- load-type
  [[key url]]
  (->> (io/resource url)
       slurp
       edn/read-string
       (assoc {} key)))

(defn- build-schema
  []
  (->> (type-file)
       (map load-type)
       (reduce merge)))

(defn load-schema
  []
  (-> (build-schema)
      (util/attach-resolvers (resolver-map))
      schema/compile))
