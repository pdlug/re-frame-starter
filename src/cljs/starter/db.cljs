(ns starter.db
  (:require [cljs.spec.alpha :as s]))

(s/def ::page keyword?)
(s/def ::route-params map?)

(s/def ::db (s/keys :req [::page ::route-params]
                    :opt [:signup/signup :flash/flash]))

(def default-db
  {::page         :homepage
   ::route-params {}})
