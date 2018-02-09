(ns dev
  (:require [io.pedestal.http :as http]
            [ns-tracker.core :as tracker]
            [starter.service :as service]
            [starter.server :as server]))

(defn start
  [& [opts]]
  (server/create-server)
  (http/start server/service-instance))

(defn stop
  []
  (http/stop server/service-instance))

(defn restart
  []
  (stop)
  (start))

(defn- ns-reload [track]
  (try
    (doseq [ns-sym (track)]
      (require ns-sym :reload))
    (catch Throwable e (.printStackTrace e))))

(defn watch
  ([] (watch ["src"]))
  ([src-paths]
   (let [track (tracker/ns-tracker src-paths)
         done (atom false)]
     (doto
       (Thread. (fn []
                  (while (not @done)
                    (ns-reload track)
                    (Thread/sleep 500))))
       (.setDaemon true)
       (.start))
     (fn [] (swap! done not)))))

(defn -main
  [& args]
  (start)
  (watch))
