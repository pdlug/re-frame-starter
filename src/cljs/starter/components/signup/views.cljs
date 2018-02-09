(ns starter.components.signup.views
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch subscribe]]
            [starter.components.signup.db]
            [starter.components.signup.events]
            [starter.components.signup.subs]))

(defn name-input
  [name]
  [:div
   [:div.form-group
    [:input.form-control
     {:type        "text"
      :value       @name
      :placeholder "Name"
      :on-change   (fn [evt]
                     (let [val (-> evt .-target .-value)]
                       (reset! name val)))}]]])

(defn email-input
  [email]
  [:div
   [:div.form-group
    [:input.form-control
     {:type        "email"
      :value       @email
      :placeholder "Email"
      :on-change   (fn [evt]
                     (let [val (-> evt .-target .-value)]
                       (reset! email val)))}]]])

(defn component
  []
  (fn []
    (let [name (reagent/atom "")
          email (reagent/atom "")]
      [:form
       {:on-submit (fn [evt]
                     (.preventDefault evt)
                     (dispatch [:signup/signup @name @email]))}
       [name-input name]
       [email-input email]
       [:button.btn.btn-primary {:type "submit"} "Submit"]])))


