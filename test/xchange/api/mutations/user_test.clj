(ns xchange.api.mutations.user-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [valid? missing-args? invalid-args?]]))

(deftest create-user

  (testing "name is required"
    (missing-args? '(:name) "mutation { create_user { id } }"))

  (testing "successfully creates a user"
    (valid? "mutation { create_user (name: \"Rich Hickey\"
                                     phone: \"1234567890\"
                                     email: \"e@mail.com\")
                                     { id } }"))

  (testing "email is correct"
    (invalid-args? '(:email) "mutation { create_user (name: \"Rich Hickey\"
                                                      email: \"incorrect.mail\")
                                                      { id } }"))

  (testing "phone is correct"
    (invalid-args? '(:phone) "mutation { create_user (name: \"Rich Hickey\"
                                                      phone: \"123456789\")
                                                      { id } }"))

  )
