package br.com.ksgprod.converter;

import java.util.Objects;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.ksgprod.controller.response.ReceivableResponse;
import br.com.ksgprod.domain.Receivable;

@Component
public class ReceivableToResponseConverter implements Function<Receivable, ReceivableResponse>{

	@Override
	public ReceivableResponse apply(Receivable receivable) {

		if(Objects.isNull(receivable)) return null;
		
		ReceivableResponse response = new ReceivableResponse()
				.document(receivable.getStoreDocument())
				.netValue(receivable.getNetValue())
				.date(receivable.getTimestamp());
		
		return response;
	}

}
