(ns xchange.utils.validations)

(defn length-in-range
  [min max val]
  (< min (count val) max))