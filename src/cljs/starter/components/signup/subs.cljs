(ns starter.components.signup.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :signup/name
  (fn [db]
    (get-in db [:signup/signup :signup/name])))

(reg-sub
  :signup/email
  (fn [db]
    (get-in db [:signup/signup :signup/email])))