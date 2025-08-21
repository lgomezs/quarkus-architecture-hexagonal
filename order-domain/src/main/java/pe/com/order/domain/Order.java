package pe.com.order.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class Order implements Serializable {

	@Serial
	private static final long serialVersionUID = -1883649250348897271L;

	private Integer id;

	private final OrderCode ordercode;

	private final OrderDescription orderdescription;

	private final List<Item> items;

	public Order(OrderCode ordercode, OrderDescription orderdescription, List<Item> items) {
		if (ordercode == null) {
			throw new IllegalArgumentException("Order code cannot be null");
		}
		if (orderdescription == null) {
			throw new IllegalArgumentException("Order description cannot be null");
		}
		if (items == null || items.isEmpty()) {
			throw new IllegalArgumentException("Items cannot be null or empty");
		}
		this.ordercode = ordercode;
		this.orderdescription = orderdescription;
		this.items = items;
	}
}
