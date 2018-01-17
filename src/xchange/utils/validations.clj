(ns xchange.utils.validations)

(defn max-length
  [max val]
  (>= max (count val)))