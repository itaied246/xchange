(ns xchange.api.mutations.comment-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [invalid-args? valid? missing-args? q]]
            [clojure.spec.alpha :as s]))

(deftest comment-spec

  (testing "body is not empty"
    (is (not (s/valid? :xchange.api.resolvers.mutations.comment/body ""))))

  (testing "body max length is 5000"
    (let [exceed-length 5001]
      (is (not (s/valid?
                 :xchange.api.resolvers.mutations.comment/body
                 (clojure.string/join
                   (repeat exceed-length "q")))))))

  (testing "offer-id is not empty"
    (is (not (s/valid?
               :xchange.api.resolvers.mutations.comment/offer-id
               ""))))

  (testing "id is not empty"
    (is (not (s/valid?
               :xchange.api.resolvers.mutations.comment/id
               "")))))

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


