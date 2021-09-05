package br.com.ksgprod.converter;

import java.util.function.BiFunction;

import org.springframework.stereotype.Component;

import br.com.ksgprod.controller.response.StoreTotalValueResponse;

@Component
public class StoreTotalValueResponseConverter implements BiFunction<String, Long, StoreTotalValueResponse>{

	@Override
	public StoreTotalValueResponse apply(String document, Long value) {

		StoreTotalValueResponse response = new StoreTotalValueResponse();
		
		return response.document(document).totalValue(value);
	}

}
