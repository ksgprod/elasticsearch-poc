package br.com.ksgprod.service;

import static br.com.ksgprod.domain.DomainModel.TIMESTAMP;
import static br.com.ksgprod.domain.Receivable.STORE_DOCUMENT;
import static br.com.ksgprod.utils.Indexes.RECEIVABLE;

import java.time.LocalDateTime;
import java.util.List;
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

import br.com.ksgprod.controller.filter.ReceivableFilter;
import br.com.ksgprod.domain.Receivable;
import br.com.ksgprod.repository.ReceivableRepository;
import br.com.ksgprod.utils.Dates;

@Service
public class ReceivableServiceImpl extends BaseService implements ReceivableService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReceivableServiceImpl.class);
	
	private ReceivableRepository repository;
	
	public ReceivableServiceImpl(RestHighLevelClient client,
			ReceivableRepository repository) {
		
		super(client, RECEIVABLE, Receivable.class);
		this.repository = repository;
	}

	@Override
	public void init() {
		
		LOGGER.info("stage=init method=ReceivableListResponse.init");
		
		for (int i = 0; i < 4; i++) {
			String document = this.getRandomDocument();
			
			for (int j = 0; j < 30; j++) {
				LocalDateTime date = this.getRandomDate();

				Receivable receivable = new Receivable()
						.storeDocument(document)
						.netValue(this.getRandomValue() * 100);
				
				receivable.setTimestamp(Dates.formatDate(date));
				this.repository.save(receivable);
			}
		}
		
		LOGGER.info("stage=end method=ReceivableListResponse.init");
	}

	@Override
	public List<Receivable> find(ReceivableFilter filter) {
		
		LOGGER.info("stage=init method=ReceivableListResponse.find by filter={}", filter);
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		
		BoolQueryBuilder query = QueryBuilders.boolQuery()
				.filter(this.getRangeDateFilter(TIMESTAMP, filter.getStartDate(), filter.getEndDate()))
				.filter(this.getTermQueryFilter(STORE_DOCUMENT, filter.getDocuments()));
		
		sourceBuilder.from(this.getInitPage(filter.getPage(), filter.getQuantity()));
		sourceBuilder.size(filter.getQuantity());
		sourceBuilder.sort(new FieldSortBuilder(TIMESTAMP).order(SortOrder.ASC));
		sourceBuilder.query(query);
		
		
		List<?> receivables = this.search(sourceBuilder);
		List<Receivable> receivableList = receivables.stream().map(r -> (Receivable) r).collect(Collectors.toList());
		
		LOGGER.info("stage=end method=ReceivableListResponse.find by filter={}", filter);
		
		return receivableList;
	}

	@Override
	public void delete() {
		
		LOGGER.info("stage=init method=ReceivableListResponse.delete");
		
		this.repository.deleteAll();
		
		LOGGER.info("stage=end method=ReceivableListResponse.delete");
		
	}

}
