(ns xchange.api.mutations.offer-it-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :as utils]
            [xchange.utils.test-data :as td]))

(defn populate-db [f]
  (td/up 20180129202409)
  (f)
  (td/down 20180129202409))

(use-fixtures :each populate-db)

(deftest ^:it create-offer

  (testing "successfully creates an offer"
    (let [query "mutation { create_offer (price: 100
                                          description: \"Great game\"
                                          platform: PC
                                          title: \"Tekken 7\")
                                          { id
                                            title
                                            platform
                                            description } }"
          actual (utils/q utils/schema query {:user-id 1})
          expected {:data {:create_offer {:id          "1"
                                          :title       "Tekken 7"
                                          :platform    "PC"
                                          :description "Great game"}}}]
      (is (= expected actual))))

  (testing "fail creating an offer without a user-id"
    (let [query "mutation { create_offer (platform: PC
                                          title: \"Tekken 7\")
                                          { id } }"
          res (utils/q utils/schema query)]
      (is (not (nil? (:errors res)))))))

(deftest ^:it update-offer

  (testing "fail creating an offer without a user-id"
    (let [query "mutation { update_offer (id:\"1\"
                                          platform: PC)
                                          { id } }"
          res (utils/q utils/schema query)]
      (is (not (nil? (:errors res)))))))
