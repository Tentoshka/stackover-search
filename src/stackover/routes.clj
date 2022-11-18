(ns stackover.routes
  (:require
   [compojure.core :refer :all]
   [stackover.handlers :as h]))

(defroutes app-routes
  (GET "/search"             request h/search)
  (GET "/-/probe/liveliness" request h/liveliness)
  h/page-not-found)
