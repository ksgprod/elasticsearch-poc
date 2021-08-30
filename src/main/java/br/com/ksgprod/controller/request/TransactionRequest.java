package br.com.ksgprod.controller.request;

import java.io.Serializable;

import com.google.gson.Gson;

public class TransactionRequest implements Serializable {

	private static final long serialVersionUID = -1633307614956706845L;
	
	private String orderId;
	
	private Long totalValue;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Long totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
