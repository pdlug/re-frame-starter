(ns starter.views
  (:require [re-frame.core :refer [dispatch subscribe]]
            [starter.pages.homepage]
            [starter.pages.not-found]))

(defmulti page (fn [name _] name))

(defmethod page :homepage [_ _]
  [starter.pages.homepage/page])

(defmethod page :default [_ _]
  [starter.pages.not-found/page])

(defn main []
  (fn []
    (let [[key params] @(subscribe [:page])]
      [:div (page key params)])))
