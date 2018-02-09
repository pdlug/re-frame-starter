(ns starter.api
  (:require [cheshire.core :as json]))

(defn success-json-response
  [data]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (json/generate-string data)})

(defn api-version
  [_]
  (success-json-response {:version "1.0.0"}))

(defn echo
  [request]
  (let [input (json/parse-string (slurp (:body request)) true)]
    (success-json-response {:data input})))
