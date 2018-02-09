(ns starter.subs
  (:require [re-frame.core :refer [reg-sub]]
            [starter.db :as db]))

(reg-sub
  :page
  (fn [db]
    [(::db/page db) (::db/route-params db)]))
