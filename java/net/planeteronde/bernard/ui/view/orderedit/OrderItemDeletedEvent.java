package net.planeteronde.bernard.ui.view.orderedit;

import net.planeteronde.bernard.backend.data.entity.OrderItem;

public class OrderItemDeletedEvent {

	private OrderItem orderItem;

	public OrderItemDeletedEvent(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}
}
