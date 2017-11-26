(ns xchange.data.user-test
  (:require [clojure.test :refer :all]
            [xchange.data.user :refer [create-user]]))

(def con "postgresql://postgres:pass@localhost/xchange")

(deftest user

  (testing "successfully creates a user with all of its properties"
    (let [res (create-user con {:name  "itai"
                                :email "itaied@gmail.com"
                                :phone "1234567"})
          id (->> res first :id)]
      (is (= 1 id)))))
