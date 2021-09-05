package br.com.ksgprod.controller.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class StoreTotalValueListResponse {
	
	private List<StoreTotalValueResponse> stores = new ArrayList<StoreTotalValueResponse>();

	public List<StoreTotalValueResponse> getStores() {
		return stores;
	}
	
	public StoreTotalValueListResponse add(StoreTotalValueResponse response) {
		if(CollectionUtils.isEmpty(this.stores))
			this.stores = new ArrayList<StoreTotalValueResponse>();
		
		this.stores.add(response);
		return this;
	}

}
