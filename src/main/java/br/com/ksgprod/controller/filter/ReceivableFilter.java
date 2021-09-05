package br.com.ksgprod.controller.filter;

import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;

public class ReceivableFilter {
	
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
	
	public ReceivableFilter documents(List<String> documents) {
		this.documents = documents;
		return this;
	}
	
	public ReceivableFilter startDate(LocalDate startDate) {
		this.startDate = startDate;
		return this;
	}
	
	public ReceivableFilter endDate(LocalDate endDate) {
		this.endDate = endDate;
		return this;
	}
	
	public ReceivableFilter page(Integer page) {
		this.page = page;
		return this;
	}
	
	public ReceivableFilter quantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
