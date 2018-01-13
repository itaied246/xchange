(ns xchange.utils.config
  (:require [struct.core :as st]
            [clojure.spec.alpha :as s]
            [com.gfredericks.test.chuck.generators :as cg]))

(def port-regex #"[1-9]\d{1,4}")
(s/def ::port (s/with-gen
                (s/and string? (partial re-matches port-regex))
                #(cg/string-from-regex port-regex)))
(s/def ::db-url string?)
(s/def ::config (s/keys :req-un [::port ::db-url]))

(s/def :config.parsed/port int?)
(s/def :config.parsed/config (s/keys :req-un [:config.parsed/port ::db-url]))

(defn- parse-config
  [conf]
  (let [port (:port conf)]
    (assoc conf :port (Integer/parseInt port))))

(defn create-config-spec
  [env]
  (let [conf (s/conform ::config env)]
    (if (s/invalid? conf)
      (throw (ex-info "Invalid input" (s/explain-data ::config env)))
      (parse-config env))))

(s/fdef create-config-spec
        :args (s/cat :env ::config)
        :ret :config.parsed/config
        :fn #(= (-> % :ret :port str)
                (-> % :args :env :port)))

(def schema
  [[:db-url st/required st/string]
   [:port st/required st/integer-str]])

(defn create-config
  [env]
  (st/validate env schema))
