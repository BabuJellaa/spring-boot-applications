package com.tech.spring.dto;

import java.util.List;

public class TownsResponse {
	public TownsResponse(List<SolrTownResponseDTO> townsData) {
		super();
		this.townsData = townsData;
	}

	public List<SolrTownResponseDTO> townsData;

	public List<SolrTownResponseDTO> getTownsData() {
		return townsData;
	}

	public void setTownsData(List<SolrTownResponseDTO> townsData) {
		this.townsData = townsData;
	}
}
