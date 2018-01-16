(ns xchange.api.resolvers.mutations.user
  (:require [com.walmartlabs.lacinia.resolve :refer [resolve-as]]
            [clojure.spec.alpha :as s]
            [xchange.models.user]))

(defmacro do-if-valid
  [spec val body]
  `(let [conform# (s/conform ~spec ~val)]
     (if (s/invalid? conform#)
       (resolve-as nil {:message (s/explain-str ~spec ~val)})
       ~body)))

(defn create-user
  [context args value]
  (do-if-valid :xchange.models.user/user args
               "ok"))
