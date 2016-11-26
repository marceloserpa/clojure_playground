(ns kafka-poc.kafka-producer)

(use 'clj-kafka.producer)

(def cfg-broker {"metadata.broker.list" "localhost:9092"
                 "serializer.class" "kafka.serializer.DefaultEncoder"
                 "partitioner.class" "kafka.producer.DefaultPartitioner"})

(def topic "test")

(defn build-message [msg]
  (message topic
    (.getBytes msg)))

(def produce
  (producer cfg-broker))

(send-message
  produce (build-message "New Message"))