package pe.com.order.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pe.com.order.domain.model.Order;
import pe.com.order.domain.model.OrderCode;
import pe.com.order.domain.model.OrderDescription;
import pe.com.order.domain.port.output.OrderEventPublisher;
import pe.com.order.domain.port.output.OrderRepository;

import java.util.List;

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

	public List<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}
}
