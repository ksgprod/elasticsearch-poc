package br.com.ksgprod.service;

import br.com.ksgprod.controller.filter.TransactionFilter;
import br.com.ksgprod.controller.response.TransactionListResponse;

public interface TransactionService {
	
	void init();
	
	TransactionListResponse find(TransactionFilter filter);
	
	void delete();

}
