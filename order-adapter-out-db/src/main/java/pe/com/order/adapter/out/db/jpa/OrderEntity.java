package pe.com.order.adapter.out.db.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String code;

	private String description;

	@OneToMany(mappedBy = "order", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderLineEntity> items;
}
