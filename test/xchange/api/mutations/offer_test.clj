(ns xchange.api.mutations.offer-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [invalid-args? valid? missing-args? q]]))

(deftest create-offer

  (testing "platform is required"
    (missing-args? '(:platform :title) "mutation { create_offer { id } }"))

  (testing "successfully creates an offer"
    (valid? "mutation { create_offer (title: \"Tekken 7\" platform: PC) { id } }"))

  (testing "price is positive"
    (invalid-args? '(:price) "mutation { create_offer (title: \"Tekken 7\" platform: PC price: -5) { id } }"))

  (testing "price is less than 1000"
    (invalid-args? '(:price) "mutation { create_offer (title: \"Tekken 7\" platform: PC price: 1000) { id } }"))

  )
