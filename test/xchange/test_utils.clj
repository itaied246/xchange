(ns xchange.test-utils
  (:require [user :refer [q]]
            [clojure.test :refer [is]]
            [xchange.api.schema :refer [load-schema]]))

(defmacro valid?
  [query]
  `(let [res# (q ~query)
         err# (->> res# :errors first)]
     (is (nil? err#))))

(defmacro missing-args?
  [args query]
  `(let [res# (q ~query)
         err# (->> res# :errors first)]
     (is (not (nil? err#)))
     (is (= ~args (:missing-arguments err#)))))
