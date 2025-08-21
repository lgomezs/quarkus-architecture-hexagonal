package pe.com.order.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pe.com.order.domain.Order;
import pe.com.order.domain.OrderCode;
import pe.com.order.domain.OrderDescription;
import pe.com.order.domain.port.OrderEventPublisher;
import pe.com.order.domain.port.OrderRepository;

@ApplicationScoped
public class OrderCaseUse {

	private final OrderRepository orderRepository;

	private final OrderEventPublisher orderEventPublisher;

	@Inject
	public OrderCaseUse(OrderRepository orderRepository, OrderEventPublisher orderEventPublisher) {
		this.orderRepository = orderRepository;
		this.orderEventPublisher = orderEventPublisher;
	}

	@Transactional
	public void handle(OrderCommandCreate orderCommandCreate) {
		final Order order = new Order(new OrderCode(orderCommandCreate.code()),
				new OrderDescription(orderCommandCreate.description()), orderCommandCreate.items());
		this.orderRepository.save(order);
		this.orderEventPublisher.publishOrderCreatedEvent("order-created", order);
	}
}
