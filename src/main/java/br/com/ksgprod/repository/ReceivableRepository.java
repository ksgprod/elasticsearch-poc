package br.com.ksgprod.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import br.com.ksgprod.domain.Receivable;

@Repository
public interface ReceivableRepository extends ElasticsearchRepository<Receivable, String> {

}
