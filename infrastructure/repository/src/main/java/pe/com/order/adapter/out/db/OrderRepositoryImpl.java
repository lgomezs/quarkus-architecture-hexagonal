package pe.com.order.adapter.out.db;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import pe.com.order.adapter.out.db.jpa.OrderEntity;
import pe.com.order.adapter.out.db.jpa.OrderLineEntity;
import pe.com.order.adapter.out.db.mapper.OrderMapper;
import pe.com.order.domain.model.Order;
import pe.com.order.domain.port.output.OrderRepository;

import java.util.List;

@Slf4j
@ApplicationScoped
public class OrderRepositoryImpl implements OrderRepository {

	@Inject
	private EntityManager entityManager;

	@Inject
	private OrderMapper orderMapper;

	@Override
	public void save(Order order) {
		final OrderEntity orderEntity = this.toEntity(order);
		log.info("Saving order: {}", orderEntity);
		this.entityManager.persist(orderEntity);
	}

	@Override
	public List<Order> findAll() {
		final List<OrderEntity> orderEntity = this.entityManager
				.createQuery("SELECT o FROM OrderEntity o  LEFT JOIN FETCH o.items ", OrderEntity.class)
				.getResultList();
		return this.orderMapper.toOrders(orderEntity);
	}

	private OrderEntity toEntity(Order order) {
		final OrderEntity orderEntity = new OrderEntity();
		orderEntity.setCode(order.getOrdercode().value());
		orderEntity.setDescription(order.getOrderdescription().value());

		order.getItems().forEach(items -> {
			final OrderLineEntity itemEntity = new OrderLineEntity();
			itemEntity.setModel(items.model());
			itemEntity.setQuantity(items.quantity());
			orderEntity.addItem(itemEntity);
		});

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
