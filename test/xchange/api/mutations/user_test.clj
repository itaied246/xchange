(ns xchange.api.mutations.user-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [valid? missing-args?]]
            [clojure.spec.alpha :as s]
            [xchange.api.resolvers.mutations.user]))

(deftest create-user-input

  (testing "phone is a 10 digit string"
    (is (s/valid? :xchange.api.resolvers.mutations.user/phone "1234567890")))

  (testing "email is a valid regex"
    (is (s/valid? :xchange.api.resolvers.mutations.user/email "u@m.com")))

  (testing "name should be valid"
    (is (s/valid? :xchange.api.resolvers.mutations.user/name "Itai Edri")))

  (testing "name is not empty"
    (is (not (s/valid? :xchange.api.resolvers.mutations.user/name ""))))

  (testing "name max length is 50"
    (let [exceed-name (clojure.string/join (repeat 51 "q"))]
      (is (not (s/valid? :xchange.api.resolvers.mutations.user/name exceed-name))))))