(ns xchange.utils.config-test
  (:require [clojure.test :refer :all]
            [xchange.utils.config :as config]))

(deftest config

  (testing "default environment is 'development'"
    (let [c (config/create-config {})]
      (is (= "development" (c config/environment)))))

  (testing "environment is configurable"
    (let [c (config/create-config {config/environment "my-env"})]
      (is (= "my-env" (c config/environment)))))

  )
