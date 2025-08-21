package pe.com.order.domain.port;

import pe.com.order.domain.Order;

public interface OrderEventPublisher {

	void publishOrderCreatedEvent(String topicName, Order event);

}
