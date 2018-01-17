(ns xchange.api.mutations.offer-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [invalid-args? valid? missing-args? q]]
            [clojure.spec.alpha :as s]))

(deftest create-offer-input

  (testing "price is positive"
    (is (not (s/valid? :xchange.api.resolvers.mutations.offer/price -5))))

  (testing "price is less than 1000"
    (is (not (s/valid? :xchange.api.resolvers.mutations.offer/price 1000))))

  (testing "description max length is 5000"
    (let [exceed-length 5001]
      (is (not (s/valid?
                 :xchange.api.resolvers.mutations.offer/description
                 (clojure.string/join
                   (take exceed-length (repeat "q"))))))))

  (testing "title max length is 100"
    (let [exceed-length 101]
      (is (not (s/valid?
                 :xchange.api.resolvers.mutations.offer/title
                 (clojure.string/join
                   (take exceed-length (repeat "q")))))))))

(deftest add-offer-comment

  (testing "successfully add a comment"
    (valid? "mutation { add_offer_comment (id: \"1\"
                                           body: \"body\")
                                           { id } }"))

  (testing "id and body are required"
    (missing-args? '(:id :body) "mutation { add_offer_comment { id } }"))

  (testing "body max length is 5000"
    (let [exceed-length 5001]
      (invalid-args? '(:body)
                     (str
                       "mutation { add_offer_comment (id: \"1\"
                                                      body: \""
                       (clojure.string/join
                         (take exceed-length (repeat "q")))
                       "\"){ id } }"))))

  )

(deftest update-offer

  (testing "successfully update an offer"
    (valid? "mutation { update_offer (id: \"1\"
                                      price: 5
                                      description: \"greate game\"
                                      platform: PC
                                      title: \"Tekken 5\")
                                      { id } }"))

  (testing "id is required"
    (missing-args? '(:id) "mutation { update_offer { id } }"))

  )

(deftest remove-offer

  (testing "successfully remove an offer"
    (valid? "mutation { remove_offer (id: \"1\") }"))

  (testing "id is required"
    (missing-args? '(:id) "mutation { remove_offer }"))

  )
