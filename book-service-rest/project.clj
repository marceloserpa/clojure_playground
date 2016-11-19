(defproject clojure-rest "0.1.0-SNAPSHOT"
  :description "REST service for documents"
  :url "http://blog.interlinked.org"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.1"]
                 [ring/ring-json "0.1.2"]
                 [ring/ring-jetty-adapter "0.3.7"]
                 [cheshire "4.0.3"]]
  :plugins [[lein-ring "0.7.3"]]
  :ring {:handler clojure-rest.core/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})