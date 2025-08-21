package pe.com.order.domain;

public record Item(Integer model, Integer quantity) {

	public Item {
		if (model == null || model <= 0) {
			throw new IllegalArgumentException("Model must be a positive integer");
		}
		if (quantity == null || quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be a positive integer");
		}
	}
}
