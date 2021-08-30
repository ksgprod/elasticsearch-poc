package br.com.ksgprod.converter;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.com.ksgprod.controller.response.ReceivableListResponse;
import br.com.ksgprod.controller.response.ReceivableResponse;
import br.com.ksgprod.domain.Receivable;

@Component
public class ReceivablesToResponseListConverter implements Function<List<?>, ReceivableListResponse>{

	@Override
	public ReceivableListResponse apply(List<?> receivables) {
		
		ReceivableListResponse response = new ReceivableListResponse();

		if(CollectionUtils.isEmpty(receivables)) return response;
		
		receivables.stream().forEach(r -> {
			
			Receivable receivable = (Receivable) r;
			
			ReceivableResponse receivableResponse = new ReceivableResponse()
					.document(receivable.getStoreDocument())
					.netValue(receivable.getNetValue())
					.date(receivable.getTimestamp());
			
			response.add(receivableResponse);
			
		});
		
		return response;
	}

}
