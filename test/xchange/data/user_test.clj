(ns xchange.data.user-test
  (:require [clojure.test :refer :all]
            [xchange.data.user :refer [create-user]]
            [environ.core :refer [env]]
            [xchange.utils.config :refer [create-config]]))

(def con (->> env create-config second :db-url))

(deftest user

  (testing "successfully creates a user with all of its properties"
    (let [res (create-user con {:name  "itai"
                                :email "itaied@gmail.com"
                                :phone "1234567"})
          id (->> res first :id)]
      (is (= 1 id)))))
