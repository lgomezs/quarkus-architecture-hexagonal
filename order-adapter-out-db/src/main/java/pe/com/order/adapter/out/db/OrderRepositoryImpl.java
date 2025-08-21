package pe.com.order.adapter.out.db;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import pe.com.order.adapter.out.db.jpa.OrderEntity;
import pe.com.order.adapter.out.db.jpa.OrderLineEntity;
import pe.com.order.domain.Order;
import pe.com.order.domain.port.OrderRepository;

@Slf4j
@ApplicationScoped
public class OrderRepositoryImpl implements OrderRepository {

	@Inject
	private EntityManager entityManager;

	@Override
	public void save(Order order) {
		final OrderEntity orderEntity = this.toEntity(order);
		log.info("Saving order: {}", orderEntity);
		this.entityManager.persist(orderEntity);
	}

	private OrderEntity toEntity(Order order) {
		final OrderEntity orderEntity = new OrderEntity();
		orderEntity.setCode(order.getOrdercode().value());
		orderEntity.setDescription(order.getOrderdescription().value());
		orderEntity.setItems(order.getItems().stream().map(items -> {
			final OrderLineEntity itemEntity = new OrderLineEntity();
			itemEntity.setModel(items.model());
			itemEntity.setQuantity(items.quantity());
			return itemEntity;
		}).toList());

		return orderEntity;
	}

	@Override
	public Order findById(Integer id) {
		return null;
	}

	@Override
	public void deleteById(Integer id) {

	}
}
