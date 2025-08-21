package pe.com.order.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import pe.com.order.domain.Order;
import pe.com.order.domain.port.OrderEventPublisher;

@Slf4j
@ApplicationScoped
public class OrderEventPublisherImpl implements OrderEventPublisher {

	@Channel("orders")
	Emitter<Record<String, String>> emitter;

	@Inject
	ObjectMapper objectMapper;

	@Override
	public void publishOrderCreatedEvent(String topicName, Order event) {
		log.info("Publishing order created event to topic: {}", topicName);
		final String message;
		try {
			message = this.objectMapper.writeValueAsString(event);
		} catch (final JsonProcessingException e) {
			log.error("Error serializing order event: {}", e.getMessage(), e);
			throw new RuntimeException(e);
		}
		this.emitter.send(Record.of(topicName, message));
	}
}
