(ns xchange.utils.config-test
  (:require [clojure.test :refer :all]
            [xchange.utils.config :as config]))

(def valid-conf
  {:db-url "db-url"
   :port   "8080"})

(deftest config

  (testing "db-url is required"
    (let [[e c] (config/create-config {})]
      (is (contains? e :db-url))))

  (testing "db-url is a string"
    (let [[e c] (config/create-config {:db-url :not-string})]
      (is (contains? e :db-url))))

  (testing "db-url is configurable"
    (let [[e c] (config/create-config valid-conf)]
      (is (nil? e))
      (is (= "db-url" (:db-url c)))))

  (testing "port is required"
    (let [[e c] (config/create-config {})]
      (is (contains? e :port))))

  (testing "port is a string"
    (let [[e c] (config/create-config {:port 8080})]
      (is (contains? e :port))))

  (testing "port coerce to integer"
    (let [[e c] (config/create-config valid-conf)]
      (is (nil? e))
      (is (= 8080 (:port c)))))

  )
