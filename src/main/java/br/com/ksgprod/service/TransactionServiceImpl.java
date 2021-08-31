package br.com.ksgprod.service;

import static br.com.ksgprod.domain.DomainModel.TIMESTAMP;
import static br.com.ksgprod.domain.Transaction.STORE_DOCUMENT;
import static br.com.ksgprod.utils.Indexes.TRANSACTION;
import static java.math.RoundingMode.HALF_DOWN;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.ksgprod.controller.filter.TransactionFilter;
import br.com.ksgprod.controller.request.TransactionRequest;
import br.com.ksgprod.controller.response.TransactionResponse;
import br.com.ksgprod.converter.TransactionToResponseConverter;
import br.com.ksgprod.domain.Transaction;
import br.com.ksgprod.exception.TransactionNotFoundException;
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
		
		for (int i = 0; i < 4; i++) {
			String document = this.getRandomDocument();
			
			for (int j = 0; j < 50; j++) {
				LocalDateTime date = this.getRandomDate();
				Long totalValue = this.getRandomValue();
				Long percentage = 200L;
				Long commission = this.calculateValueFromPercentage(totalValue, percentage);
				Transaction transaction = new Transaction()
						.storeDocument(document)
						.orderId("ORD-" + UUID.randomUUID().toString())
						.totalValue(totalValue)
						.commissionValue(commission)
						.commissionPercentage(percentage)
						.netValue(totalValue - commission);
				
				transaction.setTimestamp(Dates.formatDate(date));

				this.repository.save(transaction);
			}
		}
		
		LOGGER.info("stage=end method=TransactionServiceImpl.init");
		
	}

	@Override
	public List<Transaction> find(TransactionFilter filter) {
		
		LOGGER.info("stage=init method=TransactionServiceImpl.find by filter={}", filter);
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		
		BoolQueryBuilder query = QueryBuilders.boolQuery()
				.filter(this.getRangeDateFilter(TIMESTAMP, filter.getStartDate(), filter.getEndDate()))
				.filter(this.getTermQueryFilter(STORE_DOCUMENT, filter.getDocument()));
		
		sourceBuilder.from(this.getInitPage(filter.getPage(), filter.getQuantity()));
		sourceBuilder.size(filter.getQuantity());
		sourceBuilder.sort(new FieldSortBuilder(TIMESTAMP).order(SortOrder.ASC));
		sourceBuilder.query(query);
		
		List<?> transactions = this.search(sourceBuilder);
		
		LOGGER.info("stage=end method=TransactionServiceImpl.find by filter={}", filter);
		
		return transactions.stream().map(t -> (Transaction) t).collect(Collectors.toList());
	}

	@Override
	public void delete() {
		
		LOGGER.info("stage=init method=TransactionServiceImpl.delete");
		
		this.repository.deleteAll();
		
		LOGGER.info("stage=end method=TransactionServiceImpl.delete");
		
	}

	@Override
	public TransactionResponse update(TransactionRequest request) {
		
		LOGGER.info("stage=init method=TransactionServiceImpl.update by request={}", request);
		
		Transaction transaction = this.repository.findByOrderId(request.getOrderId());
		
		if(Objects.isNull(transaction))
			throw new TransactionNotFoundException();
		
		Long commissionValue = this.calculateValueFromPercentage(request.getTotalValue(), transaction.getCommissionPercentage());
		
		transaction
			.totalValue(request.getTotalValue())
			.commissionValue(commissionValue)
			.netValue(request.getTotalValue() - commissionValue);
		
		Transaction transactionSaved = this.repository.save(transaction);
		TransactionResponse response = this.converter.apply(transactionSaved);
		
		LOGGER.info("stage=end method=TransactionServiceImpl.update response={}", response);
		
		return response;
	}
	
	private Long calculateValueFromPercentage(Long totalValue, Long percentage) {
    	BigDecimal hundred = new BigDecimal(100);
    	BigDecimal totalValueDecimal = new BigDecimal(totalValue).divide(hundred);
    	BigDecimal percentageDecimal = new BigDecimal(percentage).divide(hundred);
		BigDecimal value = totalValueDecimal.multiply(percentageDecimal.divide(hundred))
        		.setScale(3, HALF_DOWN)
        		.setScale(2, HALF_DOWN);
		return value.multiply(hundred).longValue();
    }

}
