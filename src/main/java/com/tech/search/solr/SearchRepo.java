package com.tech.search.solr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.tech.search.solr.model.IndianTown;

import java.util.List;

public interface SearchRepo extends SolrCrudRepository<IndianTown, String> {

	List<IndianTown> findAllByState(String state);

	List<IndianTown> findAllByStateAndDistrict(String state, String district);

	Page<IndianTown> findAllByState(String state, PageRequest page);

	Page<IndianTown> findAllByStateAndDistrict(String state, String district, PageRequest page);

	@Query("state:?0 OR district:*?0* OR townName:*?0* OR category:*?0* OR state:*?0*")
	List<IndianTown> searchInMultipleFields(String searchTerm);
	
	@Query("suggestion_field:?0")
	List<IndianTown> searchInSuggestionsMultipleFields(String searchTerm);

	default List<IndianTown> searchAndLog(String searchTerm) {
		System.out.println("Executing search with term: {} "+searchTerm);
		List<IndianTown> results = searchInMultipleFields(searchTerm);
		
		return results;
	}
    default List<IndianTown> searchAndLogForSuggestions(String searchTerm) {
        System.out.println("Executing suggestion search with term: " + searchTerm);
        String[] terms = searchTerm.toLowerCase().split("\\s+");
        
        Criteria criteria=new Criteria("suggestion_field");
        
        for(String queryTerm : terms) {
        	criteria= criteria.and(new Criteria("suggestion_field").contains(queryTerm));
        }
        SimpleQuery query=new SimpleQuery(criteria);        
        String filterQuery = String.join(" AND suggestion_field:", terms);
        String combinedQuery = searchTerm + " AND suggestion_field:" + filterQuery;
        return searchInSuggestionsMultipleFields(combinedQuery);
    }
}
