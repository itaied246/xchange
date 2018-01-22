(ns xchange.data.user
  (:require [xchange.utils.db :refer [insert!]]))

(defn create-user
  [db u]
  (insert! (:db db) :users u))