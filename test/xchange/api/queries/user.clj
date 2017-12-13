(ns xchange.api.queries.user
  (:require [clojure.test :refer :all]
            [user :refer [q]]))


(deftest user

  (testing "id is required"
    (let [res (q "{ user { id } }")
          err (first (:errors res))]
      (is (not (nil? err)))
      (is (= '(:id) (:missing-arguments err)))))

  )