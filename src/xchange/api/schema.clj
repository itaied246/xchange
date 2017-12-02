(ns xchange.api.schema
  (:require [clojure.java.io :as io]
            [com.walmartlabs.lacinia.util :as util]
            [com.walmartlabs.lacinia.schema :as schema]
            [clojure.edn :as edn]))

(defn resolver-map
  []
  {:query/user-by-id (fn [context args value]
                       {:id   "KSK3=="
                        :name "Itai Edri"})
   :query/post-by-id (fn [context args value]
                       {:id      "1"
                        :user_id {:id   "10"
                                  :name "Hila"}})})

(defn- load-objects
  []
  (->> (io/resource "schema/objects.edn")
       slurp
       edn/read-string
       (assoc {} :objects)))

(defn- load-query
  []
  (->> (io/resource "schema/queries.edn")
       slurp
       edn/read-string
       (assoc {} :queries)))

(defn load-schema
  []
  (-> (merge (load-query) (load-objects))
      (util/attach-resolvers (resolver-map))
      schema/compile))
