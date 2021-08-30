package br.com.ksgprod.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import br.com.ksgprod.domain.Transaction;

@Repository
public interface TransactionRepository extends ElasticsearchRepository<Transaction, String> {
	
	Transaction findByOrderId(String orderId);

}
