package pe.com.order.domain;

public record OrderCode(String value) {

	public OrderCode {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Order code cannot be null or blank");
		}
	}
}
