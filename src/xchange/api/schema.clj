(ns xchange.api.schema
  (:require [clojure.java.io :as io]
            [com.walmartlabs.lacinia.util :as util]
            [com.walmartlabs.lacinia.schema :as schema]
            [clojure.edn :as edn]))

(defn- type-file
  []
  [[:interfaces "schema/interfaces.edn"]
   [:objects "schema/objects.edn"]
   [:enums "schema/enums.edn"]
   [:queries "schema/queries.edn"]
   [:mutations "schema/mutations.edn"]])

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
  [resolver-map]
  (-> (build-schema)
      (util/attach-resolvers resolver-map)
      schema/compile))
