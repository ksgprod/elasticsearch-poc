package br.com.ksgprod.service;

import static br.com.ksgprod.utils.Indexes.TRANSACTION;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.ksgprod.controller.filter.TransactionFilter;
import br.com.ksgprod.controller.response.TransactionListResponse;
import br.com.ksgprod.converter.TransactionToResponseConverter;
import br.com.ksgprod.domain.Transaction;
import br.com.ksgprod.repository.TransactionRepository;
import br.com.ksgprod.utils.Dates;

@Service
public class TransactionServiceImpl extends BaseService implements TransactionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	private TransactionRepository repository;
	
	private TransactionToResponseConverter converter;
	
	public TransactionServiceImpl(RestHighLevelClient client, 
			TransactionRepository repository, 
			TransactionToResponseConverter converter) {
		
		super(client, TRANSACTION, Transaction.class);
		this.repository = repository;
		this.converter = converter;
	}

	@Override
	public void init() {
		
		LOGGER.info("stage=init method=TransactionServiceImpl.init");
		
		Transaction transaction = new Transaction()
				.storeDocument("123123123")
				.orderId(UUID.randomUUID().toString())
				.totalValue(1234L)
				.commissionValue(12L)
				.commissionPercentage(2L)
				.netValue(123L);
		
		transaction.setTimestamp(Dates.formatDate(LocalDateTime.now()));
		
		this.repository.save(transaction);
		
		LOGGER.info("stage=end method=TransactionServiceImpl.init");
		
	}

	@Override
	public TransactionListResponse find(TransactionFilter filter) {
		
		LOGGER.info("stage=init method=TransactionServiceImpl.find by filter={}", filter);
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		
		sourceBuilder.query(
				QueryBuilders.boolQuery()
					.filter(this.getRangeDateFilter(filter.getStartDate(), filter.getEndDate()))
					.filter(this.getSimpleQueryStringFilter(filter.getDocument()))
				).from(filter.getPage()).size(filter.getQuantity());
		
		sourceBuilder.sort(new FieldSortBuilder("timestamp").order(SortOrder.ASC));
		
		List<?> transactions = this.search(sourceBuilder);
		TransactionListResponse response = new TransactionListResponse();
		
		transactions.stream().forEach(t -> response.add(this.converter.apply((Transaction) t)));
		
		LOGGER.info("stage=end method=TransactionServiceImpl.find by filter={}", filter);
		
		return response;
	}

	@Override
	public void delete() {
		
		LOGGER.info("stage=init method=TransactionServiceImpl.delete");
		
		this.repository.deleteAll();
		
		LOGGER.info("stage=end method=TransactionServiceImpl.delete");
		
	}

}
