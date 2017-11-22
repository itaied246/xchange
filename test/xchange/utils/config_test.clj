(ns xchange.utils.config-test
  (:require [clojure.test :refer :all]
            [xchange.utils.config :as config]))

(deftest config

  (testing "db-url is required"
    (let [c (config/create-config {})
          err (first c)]
      (is (not (nil? err)))))

  (testing "db-url is configurable"
    (let [c (config/create-config {config/db-url "db-url"})
          err (first c)]
      (is (nil? err))))

  )
