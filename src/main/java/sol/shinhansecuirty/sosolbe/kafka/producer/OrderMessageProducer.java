package sol.shinhansecuirty.sosolbe.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderMessageProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, Object message) {

        try {
            ProducerRecord<String, Object> record = new ProducerRecord<>(topic, message);
            log.info("매수 주문 요청 => {}", kafkaTemplate.send(record).get());
        } catch (Exception e) {
            log.error("매수 오류 발생 {}",e.getMessage());
        }
    }
}
