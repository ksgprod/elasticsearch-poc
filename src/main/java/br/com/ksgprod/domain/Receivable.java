package br.com.ksgprod.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import br.com.ksgprod.utils.Indexes;

@Document(indexName = Indexes.RECEIVABLE)
public class Receivable extends DomainModel<Receivable> {

	private String storeDocument;
	private Long netValue;
	
	public String getStoreDocument() {
		return storeDocument;
	}

	public Long getNetValue() {
		return netValue;
	}

	public Receivable storeDocument(String storeDocument) {
		this.storeDocument = storeDocument;
		return this;
	}

	public Receivable netValue(Long netValue) {
		this.netValue = netValue;
		return this;
	}

}
