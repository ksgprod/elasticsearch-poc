package br.com.ksgprod.service;

import br.com.ksgprod.controller.filter.TransactionFilter;
import br.com.ksgprod.controller.request.TransactionRequest;
import br.com.ksgprod.controller.response.TransactionListResponse;
import br.com.ksgprod.controller.response.TransactionResponse;

public interface TransactionService {
	
	void init();
	
	TransactionListResponse find(TransactionFilter filter);
	
	TransactionResponse update(TransactionRequest request);
	
	void delete();

}
