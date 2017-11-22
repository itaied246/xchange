(ns xchange.utils.config-test
  (:require [clojure.test :refer :all]
            [xchange.utils.config :as config]))

(deftest config

  (testing "db-url is required"
    (let [[e c] (config/create-config {})]
      (is (not (nil? e)))))

  (testing "db-url is string"
    (let [[e c] (config/create-config {config/db-url :db-url})]
      (is (not (nil? e)))))

  (testing "db-url is configurable"
    (let [[e c] (config/create-config {config/db-url "db-url"})]
      (is (nil? e))
      (is (= "db-url" (c config/db-url)))))

  )
