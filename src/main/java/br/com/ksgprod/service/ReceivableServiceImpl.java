package br.com.ksgprod.service;

import static br.com.ksgprod.utils.Indexes.RECEIVABLE;

import java.time.LocalDateTime;
import java.util.List;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.ksgprod.controller.filter.ReceivableFilter;
import br.com.ksgprod.controller.response.ReceivableListResponse;
import br.com.ksgprod.converter.ReceivableToResponseConverter;
import br.com.ksgprod.domain.Receivable;
import br.com.ksgprod.repository.ReceivableRepository;
import br.com.ksgprod.utils.Dates;

@Service
public class ReceivableServiceImpl extends BaseService implements ReceivableService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReceivableServiceImpl.class);
	
	private ReceivableRepository repository;
	
	private ReceivableToResponseConverter converter;
	
	public ReceivableServiceImpl(RestHighLevelClient client,
			ReceivableRepository repository, 
			ReceivableToResponseConverter converter) {
		
		super(client, RECEIVABLE, Receivable.class);
		this.repository = repository;
		this.converter = converter;
	}

	@Override
	public void init() {
		
		LOGGER.info("stage=init method=ReceivableListResponse.init");
		
		Receivable receivable = new Receivable()
				.storeDocument("123123123")
				.netValue(123L);
		
		receivable.setTimestamp(Dates.formatDate(LocalDateTime.now()));
		
		this.repository.save(receivable);
		
		LOGGER.info("stage=end method=ReceivableListResponse.init");
	}

	@Override
	public ReceivableListResponse find(ReceivableFilter filter) {
		
		LOGGER.info("stage=init method=ReceivableListResponse.find by filter={}", filter);
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		
		sourceBuilder.query(
				QueryBuilders.boolQuery()
					.filter(this.getRangeDateFilter(filter.getStartDate(), filter.getEndDate()))
					.filter(this.getSimpleQueryStringFilter(filter.getDocument()))
				).from(filter.getPage()).size(filter.getQuantity());
		
		sourceBuilder.sort(new FieldSortBuilder("timestamp").order(SortOrder.ASC));
		
		List<?> receivables = this.search(sourceBuilder);
		ReceivableListResponse response = new ReceivableListResponse();
		
		receivables.stream().forEach(r -> response.add(this.converter.apply((Receivable) r)));
		
		LOGGER.info("stage=end method=ReceivableListResponse.find by filter={}", filter);
		
		return response;
	}

	@Override
	public void delete() {
		
		LOGGER.info("stage=init method=ReceivableListResponse.delete");
		
		this.repository.deleteAll();
		
		LOGGER.info("stage=end method=ReceivableListResponse.delete");
		
	}

}
