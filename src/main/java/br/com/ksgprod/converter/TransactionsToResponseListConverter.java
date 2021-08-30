package br.com.ksgprod.converter;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.com.ksgprod.controller.response.TransactionListResponse;
import br.com.ksgprod.controller.response.TransactionResponse;
import br.com.ksgprod.domain.Transaction;

@Component
public class TransactionsToResponseListConverter implements Function<List<?>, TransactionListResponse>{

	@Override
	public TransactionListResponse apply(List<?> transactions) {
		
		TransactionListResponse response = new TransactionListResponse();

		if(CollectionUtils.isEmpty(transactions)) return response;
		
		transactions.stream().forEach(t -> {
			
			Transaction transaction = (Transaction) t;
			
			TransactionResponse transactionResponse = new TransactionResponse()
					.document(transaction.getStoreDocument())
					.orderId(transaction.getOrderId())
					.totalValue(transaction.getTotalValue())
					.commissionValue(transaction.getCommissionValue())
					.commissionPercentage(transaction.getCommissionPercentage())
					.netValue(transaction.getNetValue())
					.date(transaction.getTimestamp());
			
			response.add(transactionResponse);
		});
		
		return response;
	}

}
