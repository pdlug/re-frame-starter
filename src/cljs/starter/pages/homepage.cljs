(ns starter.pages.homepage
  (:require [starter.components.flash.views :as flash]
            [starter.components.signup.views :as signup]))

(defn page
  []
  [:div
   [:h1 "Hi, please sign up"]
   [flash/component]
   [:div.signup-form
    [signup/component]]])