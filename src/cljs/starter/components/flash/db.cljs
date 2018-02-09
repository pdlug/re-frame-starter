(ns starter.components.flash.db
  (:require [cljs.spec.alpha :as s]))

(s/def :flash/message string?)
(s/def :flash/priority #{"success" "info" "warning" "danger"})

(s/def :flash/flash (s/keys :req [:flash/message :flash/priority]))
