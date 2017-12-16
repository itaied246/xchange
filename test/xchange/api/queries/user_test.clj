(ns xchange.api.queries.user_test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [q]]))


(deftest user

  (testing "id is required"
    (let [res (q "{ user { id } }")
          err (->> res :errors first)]
      (is (not (nil? err)))
      (is (= '(:id) (:missing-arguments err)))))

  (testing "query by id"
    (let [res (q "{ user (id: \"1\") { id } }")
          err (first (:errors res))]
      (is (nil? err))))

  (testing "query all users"
    (let [res (q "{ users { id } }")
          err (->> res :errors first)]
      (is (nil? err))))

  )