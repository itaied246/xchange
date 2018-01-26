(ns xchange.data.user-test
  (:require [clojure.test :refer :all]
            [xchange.data.user :refer [create-user]]
            [environ.core :refer [env]]
            [xchange.utils.config :refer [create-config]]
            [xchange.components.db :as db]
            [com.stuartsierra.component :as component]))

(def db (->> env create-config :db-url db/new-db component/start :db))

(deftest ^:integration user

  (testing "successfully creates a user with all of its properties"
    (let [res (create-user db {:name  "itai"
                               :email "itaied@gmail.com"
                               :phone "1234567"})
          id (->> res first :id)]
      (is (= 1 id)))))
