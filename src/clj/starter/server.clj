(ns starter.server
  (:require
    [io.pedestal.http :as http]
    [io.pedestal.http.route :as route]
    [starter.service :as service])
  (:gen-class))

(defonce service-instance nil)

(def dev-server
  (-> service/service
      (merge {:env                   :dev
              ::http/join?           false
              ::http/routes          #(deref #'service/routes)
              ::http/allowed-origins {:creds           true
                                      :allowed-origins (constantly true)}})
      http/default-interceptors
      http/dev-interceptors))

(defn create-server
  "Standalone dev/prod mode."
  [& [opts]]
  (println "Creating server")
  (alter-var-root #'service-instance
                  (constantly (http/create-server (merge dev-server opts)))))

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (println "\nStarting server...")
  (create-server)
  (http/start service-instance))
