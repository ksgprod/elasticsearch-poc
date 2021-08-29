package br.com.ksgprod.controller.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class TransactionListResponse {

	private List<TransactionResponse> transactions;

	public List<TransactionResponse> getTransactions() {
		return transactions;
	}

	public TransactionListResponse add(TransactionResponse transaction) {
		if (CollectionUtils.isEmpty(this.transactions))
			this.transactions = new ArrayList<TransactionResponse>();
		this.transactions.add(transaction);
		return this;
	}

}
