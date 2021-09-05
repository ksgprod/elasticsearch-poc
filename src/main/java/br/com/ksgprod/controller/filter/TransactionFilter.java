package br.com.ksgprod.controller.filter;

import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;

public class TransactionFilter {
	
	private List<String> documents;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private Integer page;
	
	private Integer quantity;

	public List<String> getDocuments() {
		return documents;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public TransactionFilter documents(List<String> documents) {
		this.documents = documents;
		return this;
	}
	
	public TransactionFilter page(Integer page) {
		this.page = page;
		return this;
	}
	
	public TransactionFilter startDate(LocalDate startDate) {
		this.startDate = startDate;
		return this;
	}
	
	public TransactionFilter endDate(LocalDate endDate) {
		this.endDate = endDate;
		return this;
	}
	
	public TransactionFilter quantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
