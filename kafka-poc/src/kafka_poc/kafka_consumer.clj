(ns kafka-poc.consumer.kafka-consumer
  (:require [cheshire.core :as json]))

(use 'clj-kafka.consumer.zk)
(use 'clj-kafka.core)

(def config {"zookeeper.connect"  "localhost:2181"
             "group.id"           "my-consumer"
             "auto.offset.reset"  "smallest"
             "auto.commit.enable" "false"})

(defn decode-message [bytes]
  (let [as-string (String. bytes "UTF-8")]
    (try
      (json/parse-string as-string true)
      (catch Exception e
        {:parse_error "This message isn't json object" :original as-string}))))

(with-resource
   [c (consumer config)]
   shutdown
   (let [messages (messages c "test")]
     (doseq [message messages]
       (println (decode-message (.value message))))))

