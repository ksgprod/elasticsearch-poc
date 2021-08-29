package br.com.ksgprod.converter;

import java.util.Objects;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.ksgprod.controller.response.TransactionResponse;
import br.com.ksgprod.domain.Transaction;

@Component
public class TransactionToResponseConverter implements Function<Transaction, TransactionResponse>{

	@Override
	public TransactionResponse apply(Transaction transaction) {

		if(Objects.isNull(transaction)) return null;
		
		TransactionResponse response = new TransactionResponse()
				.document(transaction.getStoreDocument())
				.orderId(transaction.getOrderId())
				.totalValue(transaction.getTotalValue())
				.commissionValue(transaction.getCommissionValue())
				.commissionPercentage(transaction.getCommissionPercentage())
				.netValue(transaction.getNetValue())
				.date(transaction.getTimestamp());
		
		return response;
	}

}
