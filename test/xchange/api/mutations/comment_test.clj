(ns xchange.api.mutations.comment-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [invalid-args? valid? missing-args? q]]))

(deftest update-comment

  (testing "id is required"
    (missing-args? '(:id) "mutation { update_comment { id } }"))

  (testing "successfully update a comment"
    (valid? "mutation { update_comment (id: \"1\"
                                        body: \"new body\")
                                        { id } }"))

  )

(deftest remove-comment

  (testing "id is required"
    (missing-args? '(:id) "mutation { remove_comment }"))

  (testing "successfully remove a comment"
    (valid? "mutation { remove_comment (id: \"1\") }"))

  )


