package pe.com.order.adapter.out.db.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ORDER_LINES")
public class OrderLineEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer model;
	private Integer quality;
	private Integer quantity;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity order;

}
