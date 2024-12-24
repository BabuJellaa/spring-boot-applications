package com.tech.search.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.search.service.SearchMapSugegstionRequestService;
import com.tech.search.service.SolrService;
import com.tech.search.solr.model.SearchSuggestionRequest;
import com.tech.spring.dto.SolrResponseDTO;
import com.tech.spring.dto.TownsResponse;

@RestController
public class IHCLSearchController {
	
	@Autowired
	private SolrService solrService;
	
	@SuppressWarnings("unused")
	@GetMapping("/ihcl/search/suggestions")
	public void searchSuggestions(@RequestParam Map<String, String> params) {
		SearchSuggestionRequest request = SearchMapSugegstionRequestService.mapToSearchSuggestionsRequest(params);
		String query = request.getQuery();
		String source = request.getSource();
		String limit = request.getLimit();
		String latitude = request.getLatitude();
		String longitude = request.getLongitude();
		String distance = request.getDistance();	
	}
	@GetMapping("/ihcl/search")
    public List<SolrResponseDTO> search(@RequestParam String query) throws SolrServerException, IOException {
        return solrService.getsearchData(query);
    }
	
	@GetMapping("/ihcl/free-text")
	public TownsResponse performFreeTextSearch(@RequestParam String freeTextQuery) throws SolrServerException,IOException, URISyntaxException{
		return solrService.fetchData(freeTextQuery);
	}
	
}
