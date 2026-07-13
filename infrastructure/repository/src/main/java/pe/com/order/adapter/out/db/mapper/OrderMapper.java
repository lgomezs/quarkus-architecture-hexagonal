package pe.com.order.adapter.out.db.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import pe.com.order.adapter.out.db.jpa.OrderEntity;
import pe.com.order.domain.model.Order;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface OrderMapper {

	@Mapping(target = "ordercode", expression = "java(new OrderCode(entity.getCode()))")
	@Mapping(target = "orderdescription", expression = "java(new OrderDescription(entity.getDescription()))")
	@Mapping(target = "items", source = "items")
	Order toOrder(OrderEntity entity);

	List<Order> toOrders(List<OrderEntity> entities);

}
