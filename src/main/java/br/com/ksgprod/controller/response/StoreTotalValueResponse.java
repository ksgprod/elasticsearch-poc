package br.com.ksgprod.controller.response;

public class StoreTotalValueResponse {
	
	private String document;
	
	private Long totalValue;

	public String getDocument() {
		return document;
	}

	public Long getTotalValue() {
		return totalValue;
	}
	
	public StoreTotalValueResponse document(String document) {
		this.document = document;
		return this;
	}
	
	public StoreTotalValueResponse totalValue(Long totalValue) {
		this.totalValue = totalValue;
		return this;
	}

}
