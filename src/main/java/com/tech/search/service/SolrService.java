package com.tech.search.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tech.spring.dto.SolrResponseDTO;
import com.tech.spring.dto.SolrTownResponseDTO;
import com.tech.spring.dto.TownsResponse;
import com.tech.spring.utils.Constants;

@Service
public class SolrService {

	private final HttpSolrClient solrClient;

	public SolrService(@Value("${spring.data.solr.host}") String queryUrl) {
		this.solrClient = new HttpSolrClient.Builder(queryUrl+"/"+"IndianTowns").build();
	}

	@SuppressWarnings("unchecked")
	public List<SolrResponseDTO> getsearchData(String query) throws SolrServerException, IOException{
		SolrQuery solrQuery= new SolrQuery();
		solrQuery.setQuery(query);

		QueryResponse queryResponse=solrClient.query(solrQuery);

		return  queryResponse.getResults().
				stream().map(doc -> {
					SolrResponseDTO solrResponseDTO = new SolrResponseDTO();
					solrResponseDTO.setTownName(((List<String>)doc.getFieldValue("townName")).get(0));
					solrResponseDTO.setDistrict(((List<String>)doc.getFieldValue("district")).get(0));
					solrResponseDTO.setState((String)doc.getFieldValue("state"));
					solrResponseDTO.setCategory(((List<String>)doc.getFieldValue("category")).get(0));

					return solrResponseDTO;
				})
				.collect(Collectors.toList());

	}

	@SuppressWarnings("unchecked")
	public List<SolrResponseDTO> getFreeTextSearchData(String freeTextQuery)throws SolrServerException, IOException{
		SolrQuery query=new SolrQuery();

		query.setQuery(freeTextQuery);
		query.set("defType", "edismax");
		query.set("qf", "townName^10 district^5 state^7 category^2");
		query.set("pf", "townName^10 district^5 state^7");
		query.set("mm", "2<75%");
		query.setSort("score", SolrQuery.ORDER.desc);
		query.set("debugQuery", "true");
		QueryResponse queryResponse=solrClient.query(query);
		return queryResponse.getResults().
				stream().map(doc -> {
					SolrResponseDTO solrResponseDTO = new SolrResponseDTO();
					solrResponseDTO.setTownName(((List<String>)doc.getFieldValue(Constants.TOWN_NAME)).get(0));
					solrResponseDTO.setDistrict(((List<String>)doc.getFieldValue(Constants.DISTRICT)).get(0));
					solrResponseDTO.setState((String)doc.getFieldValue(Constants.STATE));
					solrResponseDTO.setCategory(((List<String>)doc.getFieldValue(Constants.CATEGORY)).get(0));

					return solrResponseDTO;
				})
				.collect(Collectors.toList());
	}

	public TownsResponse fetchFreeTextData(List<String> words) throws UnsupportedEncodingException, URISyntaxException {

		final String BASE_URL = "http://localhost:8999/solr/IndianTowns"+Constants.SELECT_HANDLER;

		String buildQueryParams=QueryBuilder.buildQueryParam(words);
		String buildFilteredQueryParams = QueryBuilder.buildFilterdQuery(words);

		String queryURL = BASE_URL + "?" + buildQueryParams + "&" + buildFilteredQueryParams;
		URI uri = new URI(queryURL);
		RestTemplate restTemplate=new RestTemplate();
		
		return mapSolrResponseToDTO(restTemplate.getForObject(uri, String.class));
	}

	public TownsResponse fetchData(String freeTextQuery) throws UnsupportedEncodingException, URISyntaxException {
		List<String> wordsToRemove = Arrays.asList(Constants.ALONG,Constants.ARE,Constants.CONTAINS,Constants.IN,Constants.WITH);
		List<String> filterWords= QueryBuilder.filterWords(freeTextQuery, wordsToRemove);
		return fetchFreeTextData(filterWords);
	}

	public static TownsResponse mapSolrResponseToDTO(String solrResponse) {
		List<SolrTownResponseDTO> dtoList = new ArrayList<SolrTownResponseDTO>();
		JSONObject jsonObject = new JSONObject(solrResponse); 
		JSONArray docs = jsonObject.getJSONObject("response").getJSONArray("docs");
		
		for(int object=0;object<docs.length();object++) {
			JSONObject docsObject= docs.getJSONObject(object);
			
			String townName=docsObject.has(Constants.TOWN_NAME) ? docsObject.getJSONArray(Constants.TOWN_NAME).getString(0) : null;
			String district = docsObject.has(Constants.DISTRICT) ? docsObject.getJSONArray(Constants.DISTRICT).getString(0) : null;
			String state = docsObject.has(Constants.STATE) ? docsObject.getString(Constants.STATE) : null;
			String category= docsObject.has(Constants.CATEGORY) ? docsObject.getJSONArray(Constants.CATEGORY).getString(0) : null;
			
			SolrTownResponseDTO responseDTO = new SolrTownResponseDTO(townName, category, state, district);
			dtoList.add(responseDTO);
		}
		return new TownsResponse(dtoList);
	}
}
