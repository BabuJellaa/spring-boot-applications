package com.tech.search.controller;

import com.tech.search.model.PagedSearchRequest;
import com.tech.search.model.SearchRequest;
import com.tech.search.service.SearchService;
import com.tech.search.solr.SearchRepo;
import com.tech.search.solr.model.IndianTown;

import org.apache.solr.client.solrj.response.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class IHCLIndianTownsAPIController {

    @Autowired
    SearchRepo repo;
    
    @Autowired
    SearchService townsService;

    @SuppressWarnings("deprecation")
	@RequestMapping(value = "/country/data/all", method = RequestMethod.GET)
    Page<IndianTown> findAllIndianTown(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) { 
        Page<IndianTown> pagedResult = repo.findAll(new PageRequest(page, size));
        return pagedResult;
    }

    @SuppressWarnings("deprecation")
	@RequestMapping(value = "/country/city-or-state/paged/search", method = RequestMethod.GET)
    Page<IndianTown> searchIndianTown(@ModelAttribute PagedSearchRequest searchRequest) {

        Page<IndianTown> townList;

        if (searchRequest.getDistrict() != null)
            townList = repo.findAllByStateAndDistrict(searchRequest.getState(), searchRequest.getDistrict(),
                    new PageRequest(searchRequest.getPage(), searchRequest.getSize()));
        else
            townList = repo.findAllByState(searchRequest.getState(),
                    new PageRequest(searchRequest.getPage(), searchRequest.getSize()));
        return townList;
    }

    @RequestMapping(value = "/india/town/search", method = RequestMethod.GET)
    List<IndianTown> searchIndianTown(@ModelAttribute SearchRequest searchRequest) {
        List<IndianTown> townList;
        if (searchRequest.getDistrict() != null)
            townList = repo.findAllByStateAndDistrict(searchRequest.getState(), searchRequest.getDistrict());
        else
            townList = repo.findAllByState(searchRequest.getState());
        return townList;
    }
    
    @RequestMapping(value = "/country/free-text-search",method = RequestMethod.GET)
    List<IndianTown> search(@RequestParam("query") String searchRequest){
    	List<IndianTown> townList = null;
    	townList=repo.searchAndLog(searchRequest);
    	return townList;
    }
    @RequestMapping(value = "/country/free-text-suggestionsearch",method = RequestMethod.GET)
    List<IndianTown> freeTextSuggestionsearch(@RequestParam("query") String searchRequest){
    	List<IndianTown> townList = null;
    	townList=repo.searchAndLogForSuggestions(searchRequest);
    	return townList;
    }
    @GetMapping("/suggestions")
    public Map<String, List<Suggestion>> getSuggestions(@RequestParam("query") String suggestionTerm) throws IOException {
        return townsService.getSuggestions(suggestionTerm);
    }
}
