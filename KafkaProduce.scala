import com.apps.kafka
import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object KafkaScalaProducer extends App {

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("client.id", "ScalaProducerExample")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("acks", "all")
  props.put("retries", "0")
  props.put("batch.size", "16384")
  props.put("linger.ms", "1")
  props.put("buffer.memory", "33554432")

  val producer: KafkaProducer[Nothing, String] = new KafkaProducer[Nothing, String](props)

  val topic = "kafka-topic-name"

  println(s"Sending Records in Kafka Topic [$topic]")

  for (i <- 1 to 500) {
    
    val record: ProducerRecord[Nothing, String] = new ProducerRecord(topic, i.toString)
    println(s"$record")
    producer.send(record)
  }

  producer.close()

}
