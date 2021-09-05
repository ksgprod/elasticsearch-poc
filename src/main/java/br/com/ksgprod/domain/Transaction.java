package br.com.ksgprod.domain;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import br.com.ksgprod.utils.Indexes;

@Document(indexName = Indexes.TRANSACTION)
public class Transaction extends DomainModel<Transaction> {
	
	public static final String STORE_DOCUMENT = "storeDocument";
	public static final String TOTAL_VALUE = "totalValue";

	@Field(type = Text, fielddata = true)
	private String storeDocument;
	
	private String orderId;
	
	private Long totalValue;
	
	private Long commissionValue;
	
	private Long commissionPercentage;
	
	private Long netValue;
	
	public String getStoreDocument() {
		return storeDocument;
	}

	public String getOrderId() {
		return orderId;
	}

	public Long getTotalValue() {
		return totalValue;
	}

	public Long getCommissionValue() {
		return commissionValue;
	}

	public Long getCommissionPercentage() {
		return commissionPercentage;
	}

	public Long getNetValue() {
		return netValue;
	}
	
	public Transaction storeDocument(String storeDocument) {
		this.storeDocument = storeDocument;
		return this;
	}

	public Transaction orderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public Transaction totalValue(Long totalValue) {
		this.totalValue = totalValue;
		return this;
	}

	public Transaction commissionValue(Long commissionValue) {
		this.commissionValue = commissionValue;
		return this;
	}

	public Transaction commissionPercentage(Long commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
		return this;
	}

	public Transaction netValue(Long netValue) {
		this.netValue = netValue;
		return this;
	}
	
}
