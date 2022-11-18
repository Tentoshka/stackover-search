(ns stackover.handlers
  (:require
   [stackover.searcher :refer [get-res]]
   [cheshire.core :refer [generate-string]]))

(defn search [{:keys [params]}]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (generate-string (get-res (:tag params)) {:pretty true})})

(defn liveliness [_]
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    "I'm alive!"})

(defn page-not-found [_]
  {:status  404
   :headers {"Content-Type" "text/plain"}
   :body    "Page not found"})
