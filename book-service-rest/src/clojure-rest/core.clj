(ns clojure-rest.core
  (:use compojure.core)
  (:use ring.adapter.jetty)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes routes-app
           (GET "/" [] "Hello World")
           (route/not-found "Not Found"))

(run-jetty routes-app {:port 9001})