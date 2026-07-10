package pe.com.order.domain.port.output;

import pe.com.order.domain.model.Order;

import java.util.List;

public interface OrderRepository {

	void save(Order order);

	List<Order> findAll();

	Order findById(Integer id);

	void deleteById(Integer id);
}
