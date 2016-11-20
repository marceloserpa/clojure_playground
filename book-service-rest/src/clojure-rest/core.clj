(ns clojure-rest.core
  (:use compojure.core)
  (:use ring.adapter.jetty)
  (:require [compojure.handler :as handler]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.conversion :refer [from-db-object]]
            [ring.middleware.json :as ring-json]
            [compojure.core :refer :all]
            [compojure.route :as route])
  (:import (org.bson.types ObjectId)))

(defroutes routes-app
   (GET "/books/" []
     (let [conn (mg/connect)
           db   (mg/get-db conn "test")]
       (mc/find-maps db :books {})))
   (POST "/books/" {params :params}
      (let [conn (mg/connect)
            db   (mg/get-db conn "test")
            author (get-in params ["author"])
            title (get-in params ["title"])]
        (mc/insert db :books {:author author :title title}))
      "OK")
   (GET "/books/:id" [id]
     (let [conn (mg/connect)
           db   (mg/get-db conn "test")]
       (mc/find-maps db :books {:_id (ObjectId. id)})))
   (context "/books/:oid" [oid]
     (PUT "/" {params :params}
       (let [conn (mg/connect)
             db   (mg/get-db conn "test")
             author (get-in params ["author"])
             title (get-in params ["title"])]
         (mc/update db "books" {:_id (ObjectId. oid)} {:author author :title title}) "ok")))
   (route/not-found "Not Found"))

(run-jetty
  (ring-json/wrap-json-response
    (ring-json/wrap-json-params routes-app)) {:port 9001})

