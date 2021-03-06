(ns xchange.api.mutations.user-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [valid? missing-args?]]
            [clojure.spec.alpha :as s]
            [xchange.api.resolvers.mutations.user]))

(deftest user-spec

  (testing "phone is a 10 digit string"
    (is (s/valid? :xchange.api.resolvers.mutations.user/phone "1234567890")))

  (testing "email is a valid regex"
    (is (s/valid? :xchange.api.resolvers.mutations.user/email "u@m.com")))

  (testing "name is not empty"
    (is (not (s/valid? :xchange.api.resolvers.mutations.user/name ""))))

  (testing "name max length is 50"
    (let [exceed-name (clojure.string/join (repeat 51 "q"))]
      (is (not (s/valid? :xchange.api.resolvers.mutations.user/name exceed-name))))))

(deftest create-user

  (testing "name is required"
    (missing-args? '(:name) "mutation { create_user { id } }"))

  (testing "valid mutation schema"
    (valid? "mutation { create_user (name: \"Itai Edri\"
                                     email: \"itai@edri.com\"
                                     phone: \"1234567890\")
                                    { id } }")))