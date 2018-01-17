(ns xchange.api.mutations.request-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [invalid-args? valid? missing-args? q]]
            [clojure.spec.alpha :as s]))

(deftest request-spec

  (testing "description max length is 5000"
    (let [exceed-length 5001]
      (is (not (s/valid?
                 :xchange.api.resolvers.mutations.request/description
                 (clojure.string/join
                   (repeat exceed-length "q")))))))

  (testing "title max length is 100"
    (let [exceed-length 101]
      (is (not (s/valid?
                 :xchange.api.resolvers.mutations.request/title
                 (clojure.string/join
                   (repeat exceed-length "q")))))))

  (testing "title is not empty"
    (is (not (s/valid?
               :xchange.api.resolvers.mutations.request/title
               ""))))

  (testing "id is not empty"
    (is (not (s/valid?
               :xchange.api.resolvers.mutations.request/id
               "")))))

(deftest create-request

  (testing "platform and title are required"
    (missing-args? '(:platform :title) "mutation { create_request { id } }"))

  (testing "successfully creates a request"
    (valid? "mutation { create_request (title: \"Tekken 7\"
                                        platform: PC
                                        description: \"Great game.\") { id } }"))

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