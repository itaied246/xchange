(ns xchange.models.user-test
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s]
            [xchange.models.user]))

(deftest user

  (testing "phone is a 10 digit string"
    (is (s/valid? :xchange.models.user/phone "1234567890")))

  (testing "email is a valid regex"
    (is (s/valid? :xchange.models.user/email "u@m.com")))

  (testing "name should be valid"
    (is (s/valid? :xchange.models.user/name "Itai Edri")))

  (testing "name is not empty"
    (is (not (s/valid? :xchange.models.user/name ""))))

  (testing "name max length is 50"
    (let [exceed-name (clojure.string/join (repeat 51 "q"))]
      (is (not (s/valid? :xchange.models.user/name exceed-name))))))
