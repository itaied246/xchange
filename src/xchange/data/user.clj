(ns xchange.data.user
  (:require [xchange.utils.db :refer [insert!]]))

(defn create-user
  [con u]
  (insert! con :users u))