package br.com.ksgprod.service;

import java.util.List;

import br.com.ksgprod.controller.filter.ReceivableFilter;
import br.com.ksgprod.domain.Receivable;

public interface ReceivableService {
	
	void init();
	
	List<Receivable> find(ReceivableFilter filter);
	
	void delete();

}
