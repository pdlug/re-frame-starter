(ns starter.service
  (:require
    [clojure.java.io :as io]
    [io.pedestal.http :as http]
    [io.pedestal.http.body-params :as body-params]
    [io.pedestal.http.route.definition :refer [defroutes]]
    [io.pedestal.http.secure-headers :as sec-headers]
    [ring.util.response :as ring-response]
    [starter.api :as api]))

(defn main
  [_]
  (ring-response/content-type
    (ring-response/response (slurp (io/resource "public/index.html")))
    "text/html"))

(def common-interceptors
  [(body-params/body-params) http/html-body])

(defroutes routes
           [[["/"
              ^:interceptors common-interceptors {:get main}
              ["/api" {:get [:api-root api/api-version]}
               ["/signup" {:post [:signup api/echo]}]]]]])

(def csp-header
  (sec-headers/content-security-policy-header
    {:default-src "'self'"
     :connect-src "'self' ws://localhost:3449/"
     :style-src   "'self' 'unsafe-inline'"
     :object-src  "'none'"
     :script-src  "'self' 'unsafe-inline' 'unsafe-eval' https: http:"}))

(def service
  {:env                     :prod
   ::http/routes            routes
   ::http/router            :linear-search
   ::http/resource-path     "/public"
   ::http/type              :jetty
   ::http/port              8080
   ::http/container-options {:h2c? true
                             :h2?  false
                             :ssl? false}
   ::http/secure-headers    {:content-security-policy-settings csp-header}})
