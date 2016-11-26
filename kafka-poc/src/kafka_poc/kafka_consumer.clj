(ns kafka-poc.consumer.kafka-consumer
  (:import (kafka.consumer KafkaStream)))

(use 'clj-kafka.consumer.zk)
(use 'clj-kafka.core)

(def config {"zookeeper.connect"  "localhost:2181"
             "group.id"           "my-consumer"
             "auto.offset.reset"  "smallest"
             ;;"auto.commit.interval.ms" "10000"
             "auto.commit.enable" "false"})

(with-resource
   [c (consumer config)]
   shutdown
   (let [messages (messages c "test")]
     (doseq [message messages]
       (println message))))

