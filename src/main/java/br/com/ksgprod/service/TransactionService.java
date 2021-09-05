package br.com.ksgprod.service;

import java.util.List;

import br.com.ksgprod.controller.filter.TransactionFilter;
import br.com.ksgprod.controller.request.TransactionRequest;
import br.com.ksgprod.controller.response.StoreTotalValueListResponse;
import br.com.ksgprod.controller.response.TransactionResponse;
import br.com.ksgprod.domain.Transaction;

public interface TransactionService {
	
	void init();
	
	List<Transaction> find(TransactionFilter filter);
	
	TransactionResponse update(TransactionRequest request);
	
	void delete();
	
	StoreTotalValueListResponse sumTotalValue(TransactionFilter filter);

}
