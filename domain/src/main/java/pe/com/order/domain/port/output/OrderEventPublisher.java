package pe.com.order.domain.port.output;

import pe.com.order.domain.model.Order;

public interface OrderEventPublisher {

	void publishOrderCreatedEvent(String topicName, Order event);

}
