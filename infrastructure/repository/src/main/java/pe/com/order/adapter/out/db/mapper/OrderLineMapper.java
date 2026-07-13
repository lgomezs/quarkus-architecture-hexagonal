package pe.com.order.adapter.out.db.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pe.com.order.adapter.out.db.jpa.OrderLineEntity;
import pe.com.order.domain.model.Item;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface OrderLineMapper {

	Item toItem(OrderLineEntity entity);

	List<Item> toItems(List<OrderLineEntity> entities);

}
