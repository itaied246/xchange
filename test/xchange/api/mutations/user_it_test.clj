(ns xchange.api.mutations.user-it-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :as utils]))

(deftest create-user

  (testing "successfully creates a user"
    (let [query "mutation { create_user (name: \"Itai Edri\"
                                         email: \"itai@edri.com\"
                                         phone: \"1234567890\")
                                         { id } }"
          actual (utils/q utils/schema query)
          expected {:data {:create_user {:id "1"}}}]
      (is (= expected actual)))))
