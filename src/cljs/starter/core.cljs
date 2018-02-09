(ns starter.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [day8.re-frame.http-fx]
            [starter.events]
            [starter.subs]
            [starter.views :as views]
            [starter.config :as config]
            [starter.routes :as routes]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (routes/initialize)
  (dev-setup)
  (mount-root))
