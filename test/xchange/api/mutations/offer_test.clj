(ns xchange.api.mutations.offer-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :refer [invalid-args? valid? missing-args? q]]
            [clojure.spec.alpha :as s]))

(deftest offer-spec

  (testing "price is positive"
    (is (not (s/valid? :xchange.api.resolvers.mutations.offer/price -5))))

  (testing "price is less than 1000"
    (is (not (s/valid? :xchange.api.resolvers.mutations.offer/price 1000))))

  (testing "description max length is 5000"
    (let [exceed-length 5001]
      (is (not (s/valid?
                 :xchange.api.resolvers.mutations.offer/description
                 (clojure.string/join
                   (repeat exceed-length "q")))))))

  (testing "title max length is 100"
    (let [exceed-length 101]
      (is (not (s/valid?
                 :xchange.api.resolvers.mutations.offer/title
                 (clojure.string/join
                   (repeat exceed-length "q")))))))

  (testing "title is not empty"
    (is (not (s/valid?
               :xchange.api.resolvers.mutations.offer/title
               ""))))

  (testing "id is not empty"
    (is (not (s/valid?
               :xchange.api.resolvers.mutations.offer/id
               "")))))

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
