(ns xchange.api.queries.user_test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [q]]))


(deftest user

  (testing "id is required"
    (let [res (q "{ user { id } }")
          err (first (:errors res))]
      (is (not (nil? err)))
      (is (= '(:id) (:missing-arguments err)))))

  (testing "query by id"
    (let [res (q "{ user (id: \"1\") { id } }")
          id (get-in res [:data :user :id])]
      (is (= "1" id))))

  )