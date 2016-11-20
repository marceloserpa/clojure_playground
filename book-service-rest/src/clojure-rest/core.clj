(ns clojure-rest.core
  (:use compojure.core)
  (:use ring.adapter.jetty)
  (:require [compojure.handler :as handler]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.conversion :refer [from-db-object]]

            [ring.middleware.json :as ring-json]

            [compojure.core :refer :all]
            [compojure.route :as route]))

(defroutes routes-app
   (GET "/books/" []
     (let [conn (mg/connect)
           db   (mg/get-db conn "test")]
       (mc/find-maps db :books {})))

   (POST "/books/" {params :params}
      (let [conn (mg/connect)
            db   (mg/get-db conn "test")
            author "ooooo"
            title "hhhhhhh"]
        (mc/insert db :books params))
      "OK")

   (route/not-found "Not Found"))

(run-jetty
  (ring-json/wrap-json-response
    (ring-json/wrap-json-params routes-app)) {:port 9001})

