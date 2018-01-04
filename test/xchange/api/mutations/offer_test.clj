(ns xchange.api.mutations.offer-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [valid? missing-args?]]))

(deftest create-offer

  (testing "platform is required"
    (missing-args? '(:platform :title) "mutation { create_offer { id } }"))

  (testing "successfully creates an offer"
    (valid? "mutation { create_offer (title: \"Tekken 7\" platform: PC) { id } }"))

  )
