(ns xchange.api.schema
  (:require [clojure.java.io :as io]
            [com.walmartlabs.lacinia.util :as util]
            [com.walmartlabs.lacinia.schema :as schema]
            [clojure.edn :as edn]
            [com.stuartsierra.component :as component]))

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

(defn resolver-map
  [component]
  (let [id {:id "1"}
        ids [id]]
    {:query/user           (fn [& _] id)
     :query/users          (fn [& _] ids)
     :query/offer          (fn [& _] id)
     :query/offers         (fn [& _] ids)
     :query/request        (fn [& _] id)
     :query/requests       (fn [& _] ids)
     :mutation/create-user (fn [& _] id)}))

(defn load-schema
  [component]
  (-> (build-schema)
      (util/attach-resolvers (resolver-map component))
      schema/compile))

(defrecord SchemaProvider [schema]

  component/Lifecycle

  (start [this]
    (assoc this :schema (load-schema this)))

  (stop [this]
    (assoc this :schema nil)))

(defn new-schema-provider
  []
  {:schema-provider (map->SchemaProvider {})})
