(ns stackover.core
  (:require
   [stackover.routes :as r]
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.middleware.params :refer [wrap-params]]
   [ring.middleware.keyword-params :refer [wrap-keyword-params]]
   [compojure.core :refer [routes]])
  (:gen-class))

(def server nil)

(def naked-app
  (routes r/app-routes))

(def app
  (-> naked-app
      wrap-keyword-params
      wrap-params))

(defn start!
  "Starts the server"
  []
  (alter-var-root
   (var server)
   (fn [server]
     (if-not server
       (run-jetty app {:port 8080 :join? false})
       server))))

(defn stop!
  "Stops the server"
  []
  (alter-var-root
   (var server)
   (fn [server]
     (when server
       (.stop server))
     nil)))

(defn run-server
  "Starts the server if it is not already running."
  []
  (stop!)
  (start!))

(defn -main
  "I don't do a whole lot."
  [& _x]
  (run-server))

(comment
  (run-server)

  (start!)

  (stop!)

  )
