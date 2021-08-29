package br.com.ksgprod.service;

import br.com.ksgprod.controller.filter.ReceivableFilter;
import br.com.ksgprod.controller.response.ReceivableListResponse;

public interface ReceivableService {
	
	void init();
	
	ReceivableListResponse find(ReceivableFilter filter);
	
	void delete();

}
