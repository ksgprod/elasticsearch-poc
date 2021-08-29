package br.com.ksgprod.controller.response;

import java.io.Serializable;

import com.google.gson.Gson;

public class ReceivableResponse implements Serializable {

	private static final long serialVersionUID = -8922574258883198784L;
	
	private String document;
	private Long netValue;
	private String date;
	
	public String getDocument() {
		return document;
	}

	public Long getNetValue() {
		return netValue;
	}
	
	public String getDate() {
		return date;
	}

	public ReceivableResponse document(String document) {
		this.document = document;
		return this;
	}

	public ReceivableResponse netValue(Long netValue) {
		this.netValue = netValue;
		return this;
	}
	
	public ReceivableResponse date(String date) {
		this.date = date;
		return this;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
