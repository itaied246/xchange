(ns xchange.utils.validations
  (:require [clojure.spec.alpha :as s]
            [com.walmartlabs.lacinia.resolve :refer [resolve-as]]))

(defn max-length
  [max val]
  (>= max (count val)))

(defmacro do-if-valid
  [spec val body]
  `(let [conform# (s/conform ~spec ~val)]
     (if (s/invalid? conform#)
       (resolve-as nil {:message (s/explain-str ~spec ~val)})
       ~body)))