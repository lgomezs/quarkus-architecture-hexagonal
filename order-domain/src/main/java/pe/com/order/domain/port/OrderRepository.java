package pe.com.order.domain.port;

import pe.com.order.domain.Order;

public interface OrderRepository {

	void save(Order order);

	Order findById(Integer id);

	void deleteById(Integer id);
}
