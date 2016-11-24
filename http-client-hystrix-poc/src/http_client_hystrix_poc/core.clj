(ns http-client-hystrix-poc.core
  (:require [clj-http.client :as client]
            [com.netflix.hystrix.core :as hystrix]))

(defn find-people-fallback [id]
  "fallback actived ")

(defn find-people [id]
  (let [url (str "http://swapi.co/api/people/" id )]
    (client/get url)))

(hystrix/defcommand find-people-command
  {:hystrix/fallback-fn find-people-fallback}
  [id]
  (find-people id))

(System/setProperty "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds" "200000")

(println (find-people-command 1))
