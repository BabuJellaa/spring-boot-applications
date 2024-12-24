package com.tech.spring.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import com.tech.spring.model.Hotel;

@Repository
public interface HotelRepository  extends SolrCrudRepository<Hotel, String>{
	
}
