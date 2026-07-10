package pe.com.order.domain.model;

public record OrderCode(String value) {

	public OrderCode {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Order code cannot be null or blank");
		}
	}
}
