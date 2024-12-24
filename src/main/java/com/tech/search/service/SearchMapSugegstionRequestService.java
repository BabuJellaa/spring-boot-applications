package com.tech.search.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tech.search.solr.model.SearchSuggestionRequest;

public class SearchMapSugegstionRequestService {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	/*
	 * public solrResponseDTO getSolrResponse() { String solrURL="";
	 * ResponseEntity<> }
	 */
	
	public static SearchSuggestionRequest mapToSearchSuggestionsRequest(Map<String, String> searchParams) {
		SearchSuggestionRequest request=new SearchSuggestionRequest();
		request.setQuery(searchParams.get("query"));
		request.setLimit("limit");
		request.setSource("source");
		request.setLatitude("latitude");
		request.setLongitude("longitude");
		request.setDistance("distance");
		
		return request;
	}
}
