package com.example.bikestores.model;

import java.io.Serializable;

/**
 * The primary key class for the stocks database table.
 * 
 */

public class StockPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int product;

	private int store;

	public int getStoreId() {
		return this.store;
	}
	public void setStoreId(int storeId) {
		this.store = storeId;
	}
	public int getProductId() {
		return this.product;
	}
	public void setProductId(int productId) {
		this.product = productId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockPK other = (StockPK) obj;
		if (product != other.product)
			return false;
		if (store != other.store)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + product;
		result = prime * result + store;
		return result;
	}

}