(ns xchange.data.user
  (:require [xchange.utils.db :refer [insert! query]]
            [honeysql.core :as sql]))

(defn create-user
  [db u]
  (insert! (:db db) :users u))

(defn find-user-by-id
  [db id]
  (query (:db db)
         (sql/format
           (sql/build :select :*
                      :from :users
                      :where [:= :id id]))))