(ns xchange.api.mutations.offer-it-test
  (:require [clojure.test :refer :all]
            [xchange.test-utils :as utils]))

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
          actual (utils/q utils/schema query)
          expected {:data {:create_offer {:id          "1"
                                          :title       "Tekken 7"
                                          :platform    "PC"
                                          :description "Great game"}}}]
      (is (= expected actual)))))
