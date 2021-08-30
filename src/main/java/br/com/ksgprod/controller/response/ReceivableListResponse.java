package br.com.ksgprod.controller.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class ReceivableListResponse {

	private List<ReceivableResponse> receivables = new ArrayList<ReceivableResponse>();

	public List<ReceivableResponse> getReceivables() {
		return receivables;
	}

	public ReceivableListResponse add(ReceivableResponse receivable) {
		if (CollectionUtils.isEmpty(this.receivables))
			this.receivables = new ArrayList<ReceivableResponse>();
		this.receivables.add(receivable);
		return this;
	}

}
