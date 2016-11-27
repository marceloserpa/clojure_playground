(ns kafka-poc.kafka-producer
  (:require [cheshire.core :as json]))

(use 'clj-kafka.producer)

(def cfg-broker {"metadata.broker.list" "localhost:9092"
                 "serializer.class" "kafka.serializer.DefaultEncoder"
                 "partitioner.class" "kafka.producer.DefaultPartitioner"})

(def topic "test")

(defn encode-message [json-message]
  (message topic
     (.getBytes (json/generate-string json-message))))

(def produce
  (producer cfg-broker))

(send-message
  produce (encode-message {:name "Marcelo" :role "Developer"}))