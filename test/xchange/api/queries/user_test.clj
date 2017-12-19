(ns xchange.api.queries.user_test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [valid? missing-args?]]))

(deftest user

  (testing "id is required"
    (missing-args? '(:id) "{ user { id } }"))

  (testing "query by id"
    (valid? "{ user (id: \"1\") { id } }"))

  (testing "query all users"
    (valid? "{ users { id } }"))

  )