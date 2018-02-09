(ns starter.components.signup.db
  (:require [cljs.spec.alpha :as s]))

(s/def :signup/name string?)

(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+$")
(s/def :signup/email (s/and string? #(re-matches email-regex %)))

(s/def :signup/signup (s/keys :opt [:signup/name :signup/email]))
