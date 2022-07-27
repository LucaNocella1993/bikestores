package com.example.bikestores.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the stocks database table.
 * 
 */
@Entity
@Table(name="stocks")
@IdClass(StockPK.class)
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;

	private int quantity;

	@Id
	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id", insertable = false, updatable = false)
	private Product product;

	@Id
	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="store_id", insertable = false, updatable = false)
	private Store store;

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}