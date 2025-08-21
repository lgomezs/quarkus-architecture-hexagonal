package pe.com.order.application;

import pe.com.order.domain.Item;

import java.util.List;

public record OrderCommandCreate(String code, String description, List<Item> items) {

	public OrderCommandCreate {
		if (code == null || code.isBlank()) {
			throw new IllegalArgumentException("Order code cannot be null or blank");
		}
		if (description == null || description.isBlank()) {
			throw new IllegalArgumentException("Order description cannot be null or blank");
		}
		if (items == null || items.isEmpty()) {
			throw new IllegalArgumentException("Items cannot be null or empty");
		}
	}
}
