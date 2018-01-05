(ns xchange.api.mutations.offer-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [invalid-args? valid? missing-args? q]]))

(deftest create-offer

  (testing "platform and title are required"
    (missing-args? '(:platform :title) "mutation { create_offer { id } }"))

  (testing "successfully creates an offer"
    (valid? "mutation { create_offer (title: \"Tekken 7\"
                                      platform: PC
                                      price: 100
                                      description: \"Great game.\") { id } }"))

  (testing "price is positive"
    (invalid-args? '(:price) "mutation { create_offer (title: \"Tekken 7\"
                                                        platform: PC
                                                        price: -5)
                                                        { id } }"))

  (testing "price is less than 1000"
    (invalid-args? '(:price) "mutation { create_offer (title: \"Tekken 7\"
                                                        platform: PC
                                                        price: 1000)
                                                        { id } }"))

  (testing "description max length is 5000"
    (let [exceed-length 5001]
      (invalid-args? '(:description)
                     (str
                       "mutation { create_offer (title: \"Tekken 7\"
                                                 platform: PC
                                                 description: \""
                       (clojure.string/join
                         (take exceed-length (repeat "q")))
                       "\"){ id } }"))))

  (testing "title max length is 100"
    (let [exceed-length 101]
      (invalid-args? '(:title)
                     (str
                       "mutation { create_offer (platform: PC
                                                 title: \""
                       (clojure.string/join
                         (take exceed-length (repeat "q")))
                       "\"){ id } }"))))

  )

(deftest add-offer-comment

  (testing "successfully add a comment"
    (valid? "mutation { add_offer_comment (offer_id: \"1\"
                                           body: \"body\")
                                           { id } }"))

  (testing "offer-id and body are required"
    (missing-args? '(:offer_id :body) "mutation { add_offer_comment { id } }"))

  (testing "body max length is 5000"
    (let [exceed-length 5001]
      (invalid-args? '(:body)
                     (str
                       "mutation { add_offer_comment (offer_id: \"1\"
                                                      body: \""
                       (clojure.string/join
                         (take exceed-length (repeat "q")))
                       "\"){ id } }"))))

  )
