(ns user
  (:require
    [com.walmartlabs.lacinia :refer [execute]]
    [clojure.walk :as walk]
    [com.walmartlabs.lacinia.pedestal :as lp]
    [io.pedestal.http :as http]
    [clojure.java.browse :refer [browse-url]]
    [xchange.api.schema :refer [load-schema]]
    [xchange.api.resolvers :refer [resolver-map]])
  (:import (clojure.lang IPersistentMap)))

(defn simplify
  "Converts all ordered maps nested within the map into standard hash maps, and
   sequences into vectors, which makes for easier constants in the tests, and eliminates ordering problems."
  [m]
  (walk/postwalk
    (fn [node]
      (cond
        (instance? IPersistentMap node)
        (into {} node)

        (seq? node)
        (vec node)

        :else
        node))
    m))

(defn q
  ([schema query-string] (q schema query-string nil))
  ([schema query-string context]
   (-> (execute schema query-string nil context)
       simplify)))

(defonce server nil)

(defn start-server
  [_]
  (let [schema (load-schema resolver-map)
        server (-> schema
                   (lp/service-map {:graphiql true})
                   http/create-server
                   http/start)]
    (browse-url "http://localhost:8888/")
    server))

(defn stop-server
  [server]
  (http/stop server)
  nil)

(defn start
  []
  (alter-var-root #'server start-server)
  :started)

(defn stop
  []
  (alter-var-root #'server stop-server)
  :stopped)
