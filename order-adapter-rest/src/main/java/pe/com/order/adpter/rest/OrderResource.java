package pe.com.order.adpter.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.com.order.application.OrderCaseUse;
import pe.com.order.application.OrderCommandCreate;

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
}
