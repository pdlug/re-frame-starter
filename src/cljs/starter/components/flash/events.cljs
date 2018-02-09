(ns starter.components.flash.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [starter.events :as events]))

(reg-event-db
  :flash/set-flash
  events/default-interceptors
  (fn [db [message priority]]
    (assoc db :flash/flash {:flash/message message
                            :flash/priority priority})))

(reg-event-db
  :flash/clear-flash
  events/default-interceptors
  (fn [db _]
    (dissoc db :flash/flash)))