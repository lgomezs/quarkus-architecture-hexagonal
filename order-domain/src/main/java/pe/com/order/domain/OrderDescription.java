package pe.com.order.domain;

public record OrderDescription(String value) {

	public OrderDescription {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Order description cannot be null or blank");
		}
	}
}
