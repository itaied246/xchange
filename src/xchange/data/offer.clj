(ns xchange.data.offer
  (:require [xchange.utils.db :refer [insert! query]]
            [honeysql.core :as sql]))

(defn create-offer
  [db offer]
  (insert! db :offers offer))
