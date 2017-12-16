(ns xchange.api.queries.offer-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [q]]))

(deftest offer

  (testing "id is required"
    (let [res (q "{ offer { id } }")
          err (->> res :errors first)]
      (is (not (nil? err)))
      (is (= '(:id) (:missing-arguments err)))))

  (testing "query by id"
    (let [res (q "{ offer (id: \"1\") { id } }")
          err (first (:errors res))]
      (is (nil? err))))

  )
