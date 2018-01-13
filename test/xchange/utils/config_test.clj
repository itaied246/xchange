(ns xchange.utils.config-test
  (:require [xchange.utils.config :as config]
            [xchange.test-utils :refer (defspec-test)]))

(defspec-test create-config `config/create-config-spec)
