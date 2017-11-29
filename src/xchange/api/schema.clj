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

(defn load-schema
  []
  (-> (io/resource "schema/schema.edn")
      slurp
      edn/read-string
      (util/attach-resolvers (resolver-map))
      schema/compile))
