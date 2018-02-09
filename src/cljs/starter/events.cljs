(ns starter.events
  (:require [cljs.spec.alpha :as s]
            [re-frame.core :refer [reg-event-db reg-event-fx after trim-v]]
            [day8.re-frame.http-fx]
            [ajax.core :as ajax]
            [starter.config :as config]
            [starter.db :as db]))

(defn check-and-throw
  "Throw an exception if db doesn't match the spec."
  [a-spec db]
  (when-not (s/valid? a-spec db)
    (throw (ex-info (str "spec check failed: " (s/explain-str a-spec db)) {}))))

(def check-spec-interceptor
  (after (partial check-and-throw ::db/db)))

(def default-interceptors
  (if config/debug?
    [check-spec-interceptor trim-v]
    [trim-v]))

(reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(reg-event-db
  :set-active-page
  default-interceptors
  (fn [db [page params]]
    (-> db
        (assoc ::db/page page)
        (assoc ::db/route-params params))))
