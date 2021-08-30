package br.com.ksgprod.service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.nonNull;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang.RandomStringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import br.com.ksgprod.exception.SearchException;
import br.com.ksgprod.utils.Dates;

public class BaseService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

	private RestHighLevelClient client;
	
	private String index;
	
	private Class<?> clazz;
	
	public BaseService(RestHighLevelClient client, String index, Class<?> clazz) {
		super();
		this.client = client;
		this.index = index;
		this.clazz = clazz;
	}

	public List<?> search(SearchSourceBuilder sourceBuilder) {
		
		List<?> results = null;
		
		LOGGER.info("stage=init method=BaseService.search");
		
		try {
			SearchRequest searchRequest = new SearchRequest().indices(this.index);
			searchRequest.source(sourceBuilder);
			
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHit[] searchHits = searchResponse.getHits().getHits();
			
			results = Arrays.stream(searchHits)
					.map(hit -> new Gson().fromJson(hit.getSourceAsString(), this.clazz))
					.collect(Collectors.toList());
			
			LOGGER.info("stage=end method=BaseService.search");
			
		} catch (IOException e) {
			LOGGER.error("stage=error method=BaseService.search error={}", e.getMessage());
			throw new SearchException(e.getMessage());
		}

		return results;
	}
	
	public RangeQueryBuilder getRangeDateFilter(LocalDate startDate, LocalDate endDate) {
		
		LocalDateTime init = nonNull(startDate) ? Dates.getInitialTimestampOfDay(startDate) : null;
		LocalDateTime end = nonNull(endDate) ? Dates.getFinalTimestampOfDay(endDate) : null;
		
		RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("timestamp").from(init).to(end);
		
		return queryBuilder;
		
	}
	
	public QueryBuilder getTermQueryFilter(String field, String filter) {
		return nonNull(filter) ? termQuery(field, filter) : matchAllQuery();
	}
	
	public Long getRandomValue() {
	    return (long) new Random().ints(1000, 20000).findFirst().getAsInt();
	}
	
	public String getRandomDocument() {
	    return RandomStringUtils.random(14, FALSE, TRUE);
	}
	
	public LocalDateTime getRandomDate() {
		int plusDays = new Random().ints(1, 10).findFirst().getAsInt();
		return LocalDateTime.now().plusDays(plusDays);
	}

}
