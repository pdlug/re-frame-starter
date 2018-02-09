(ns starter.routes
  (:import [goog.history Html5History])
  (:require
    [bidi.bidi :as bidi]
    [goog.events :as events]
    [goog.history.EventType :as EventType]
    [re-frame.core :as re-frame :refer [dispatch]]))

(def routes
  ["/" {"" :homepage}])

(defn get-token
  []
  (str js/window.location.pathname js/window.location.search))

(defn initialize
  []
  (doto (Html5History.)
    (events/listen
      EventType/NAVIGATE
      (fn [_]
        (if-let [route (bidi/match-route routes (get-token))]
          (dispatch [:set-active-page
                     (:handler route)
                     (or (:route-params route) {})])
          (dispatch [:set-active-page :unknown {}]))))
    (.setEnabled true)))

(defn path-for
  [name & params]
  (apply bidi/path-for (concat [routes name] params)))
