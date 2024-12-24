package com.tech.search.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SuggesterResponse;
import org.apache.solr.client.solrj.response.Suggestion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
	
	@Value("${spring.data.solr.host}")
	private String solrUrl;
	@Value("${spring.data.solr.collection}")
	private String collectionName;
	
	public Map<String, List<Suggestion>> getSuggestions(String searchTerm) throws IOException{
		try(SolrClient client= new HttpSolrClient.Builder(solrUrl+"/"+collectionName).build()) {
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setRequestHandler("/suggest");
			solrQuery.set("q", searchTerm);
			solrQuery.set("wt", "json");
			solrQuery.set("suggest", "true");
			solrQuery.set("suggest.count", 10);
			
			System.out.println("Solr Query: " + solrQuery);
			
            String collectionName = "IndianTowns"; 
            String requestHandler = "/suggest";
            String queryString = solrQuery.toQueryString();
            String fullUrl = solrUrl + "/" + collectionName + requestHandler + queryString;
            System.out.println("Solr full URL.."+fullUrl);
			
			QueryResponse response = client.query(solrQuery);
			System.out.println("Query Response."+response);
            SuggesterResponse suggesterResponse = response.getSuggesterResponse();
            Map<String, List<Suggestion>> suggestions = suggesterResponse.getSuggestions();
            System.out.println("Suggestions Size..!!"+suggestions.size());
            return suggestions;
		}
		catch (Exception e) {
            e.printStackTrace();
            return Map.of(); // Return empty map in case of an error
        }
	}
}
