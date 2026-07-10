package pe.com.order.adapter.out.kafka.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class OrderEvent implements Serializable {
	@Serial
	private static final long serialVersionUID = 1887391159601754663L;
	private Integer id;
	private String code;
	private String description;
	// private final List<OrderLineEvent> items = new ArrayList<>();
}
