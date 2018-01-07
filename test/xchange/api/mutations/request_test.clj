(ns xchange.api.mutations.request-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [invalid-args? valid? missing-args? q]]))

(deftest create-request

  (testing "platform and title are required"
    (missing-args? '(:platform :title) "mutation { create_request { id } }"))

  (testing "successfully creates a request"
    (valid? "mutation { create_request (title: \"Tekken 7\"
                                        platform: PC
                                        description: \"Great game.\") { id } }"))

  (testing "description max length is 5000"
    (let [exceed-length 5001]
      (invalid-args? '(:description)
                     (str
                       "mutation { create_request (title: \"Tekken 7\"
                                                   platform: PC
                                                   description: \""
                       (clojure.string/join
                         (take exceed-length (repeat "q")))
                       "\"){ id } }"))))

  (testing "title max length is 100"
    (let [exceed-length 101]
      (invalid-args? '(:title)
                     (str
                       "mutation { create_request (platform: PC
                                                   title: \""
                       (clojure.string/join
                         (take exceed-length (repeat "q")))
                       "\"){ id } }"))))

  )

(deftest add-request-comment

  (testing "successfully add a comment"
    (valid? "mutation { add_request_comment (id: \"1\"
                                             body: \"body\")
                                             { id } }"))

  (testing "id and body are required"
    (missing-args? '(:id :body) "mutation { add_request_comment { id } }"))

  (testing "body max length is 5000"
    (let [exceed-length 5001]
      (invalid-args? '(:body)
                     (str
                       "mutation { add_request_comment (id: \"1\"
                                                        body: \""
                       (clojure.string/join
                         (take exceed-length (repeat "q")))
                       "\"){ id } }"))))

  )

(deftest update-request

  (testing "successfully update a request"
    (valid? "mutation { update_request (id: \"1\"
                                        description: \"greate game\"
                                        platform: PC
                                        title: \"Tekken 5\")
                                        { id } }"))

  (testing "id is required"
    (missing-args? '(:id) "mutation { update_request { id } }"))

  )

(deftest remove-request

  (testing "successfully remove a request"
    (valid? "mutation { remove_request (id: \"1\") }"))

  (testing "id is required"
    (missing-args? '(:id) "mutation { remove_request }"))

  )