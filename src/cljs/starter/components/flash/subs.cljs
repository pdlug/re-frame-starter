(ns starter.components.flash.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :flash/flash
  (fn [db]
    (:flash/flash db)))
