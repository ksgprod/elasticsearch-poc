package br.com.ksgprod.controller.response;

import java.io.Serializable;

import com.google.gson.Gson;

public class TransactionResponse implements Serializable {

	private static final long serialVersionUID = -8922574258883198784L;
	
	private String document;
	private String orderId;
	private Long totalValue;
	private Long commissionValue;
	private Long commissionPercentage;
	private Long netValue;
	private String date;
	
	public String getDocument() {
		return document;
	}

	public String getOrderId() {
		return orderId;
	}

	public Long getTotalValue() {
		return totalValue;
	}

	public Long getCommissionValue() {
		return commissionValue;
	}

	public Long getCommissionPercentage() {
		return commissionPercentage;
	}

	public Long getNetValue() {
		return netValue;
	}
	
	public String getDate() {
		return date;
	}

	public TransactionResponse document(String document) {
		this.document = document;
		return this;
	}

	public TransactionResponse orderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public TransactionResponse totalValue(Long totalValue) {
		this.totalValue = totalValue;
		return this;
	}

	public TransactionResponse commissionValue(Long commissionValue) {
		this.commissionValue = commissionValue;
		return this;
	}

	public TransactionResponse commissionPercentage(Long commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
		return this;
	}

	public TransactionResponse netValue(Long netValue) {
		this.netValue = netValue;
		return this;
	}
	
	public TransactionResponse date(String date) {
		this.date = date;
		return this;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
