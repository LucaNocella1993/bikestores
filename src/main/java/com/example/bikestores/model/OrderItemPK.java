package com.example.bikestores.model;

import java.io.Serializable;

/**
 * The primary key class for the order_items database table.
 * 
 */

public class OrderItemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int order;

	private int product;

	public int getOrderId() {
		return this.order;
	}
	public void setOrderId(int orderId) {
		this.order = orderId;
	}
	public int getItemId() {
		return this.product;
	}
	public void setItemId(int itemId) {
		this.product = itemId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderItemPK)) {
			return false;
		}
		OrderItemPK castOther = (OrderItemPK)other;
		return 
				(this.order == castOther.order)
				&& (this.product == castOther.product);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.order;
		hash = hash * prime + this.product;

		return hash;
	}
}