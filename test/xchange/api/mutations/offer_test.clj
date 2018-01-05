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
    (invalid-args? '(:price) "mutation { create_offer (title: \"Tekken 7\" platform: PC price: -5) { id } }"))

  (testing "price is less than 1000"
    (invalid-args? '(:price) "mutation { create_offer (title: \"Tekken 7\" platform: PC price: 1000) { id } }"))

  (testing "description max length is 5000"
    (let [max-length 5001]
      (invalid-args? '(:description)
                     (str
                       "mutation { create_offer (title: \"Tekken 7\"
                                   platform: PC
                                   description: \""
                       (clojure.string/join
                         (take max-length (repeat "q")))
                       "\"){ id } }"))))

  )
