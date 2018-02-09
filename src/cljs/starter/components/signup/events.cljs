(ns starter.components.signup.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [starter.events :as events]
            [ajax.core :as ajax]))

(reg-event-fx
  :signup/signup
  events/default-interceptors
  (fn [{:keys [db]} [name email]]
    {:db         (assoc db :signup/signup {:signup/name  name
                                           :signup/email email})
     :http-xhrio {:method          :post
                  :uri             "/api/signup"
                  :timeout         8000
                  :params          {:name  name
                                    :email email}
                  :format          (ajax/json-request-format)
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:signup/success]
                  :on-failure      [:signup/error]}}))

(reg-event-db
  :signup/success
  (fn [db [_ result]]
    (assoc db :flash/flash
              {:flash/priority "success"
               :flash/message  (str "<b>Sign up success, server said:</b> "
                                    result)})))

(reg-event-db
  :signup/error
  (fn [db [_ result]]
    (assoc db :flash/flash
              {:flash/priority "danger"
               :flash/message  (str "<b>Sign up error! Server said:</b> "
                                    result)})))
