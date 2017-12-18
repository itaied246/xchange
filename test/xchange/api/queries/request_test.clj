(ns xchange.api.queries.request-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [valid? missing-args?]]))

(deftest request

  (testing "id is required"
    (missing-args? '(:id) "{ request { id } }"))

  (testing "query by id"
    (valid? "{ request (id: \"1\") { id } }"))

  (testing "query multiple requests"
    (valid? "{ requests { id } }"))

  )
