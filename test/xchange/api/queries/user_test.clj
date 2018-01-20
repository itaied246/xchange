(ns xchange.api.queries.user_test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [valid? missing-args?]]))

(deftest user

  (testing "id is required"
    (missing-args? '(:id) "{ user { id } }"))

  (testing "valid query schema"
    (valid? "{ user (id: \"1\")
                    { id
                      created_at
                      name
                      email
                      phone
                      offers { id }
                      requests { id }
                      comments { id } } }")))

(deftest users

  (testing "valid query schema"
    (valid? "{ users { id } }")))