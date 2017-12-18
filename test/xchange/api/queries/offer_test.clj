(ns xchange.api.queries.offer-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [valid? missing-args?]]))

(deftest offer

  (testing "id is required"
    (missing-args? "{ offer { id } }" '(:id)))

  (testing "query by id"
    (valid? "{ offer (id: \"1\") { id } }"))

  )
