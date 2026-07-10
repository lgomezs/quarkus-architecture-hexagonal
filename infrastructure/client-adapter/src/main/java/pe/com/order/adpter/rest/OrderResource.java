package pe.com.order.adpter.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.com.order.application.OrderCaseUse;
import pe.com.order.application.OrderCommandCreate;
import pe.com.order.domain.model.Order;

import java.util.List;

@Path("/v1/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

	private final OrderCaseUse orderCaseUse;

	@Inject
	public OrderResource(OrderCaseUse orderCaseUse) {
		this.orderCaseUse = orderCaseUse;
	}

	@POST
	public void createOrder(OrderCommandCreate orderRequest) {
		this.orderCaseUse.handle(orderRequest);
	}

	@GET
	public List<Order> getOrders() {
		return this.orderCaseUse.getAllOrders();
	}

}
