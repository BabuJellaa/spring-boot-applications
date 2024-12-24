package com.tech.spring.dto;

public class SolrTownResponseDTO {
	public SolrTownResponseDTO(String townName, String category, String state, String district) {
		super();
		this.townName = townName;
		this.category = category;
		this.state = state;
		this.district = district;
	}
	private String townName;
	private String category;
	private String state;
	private String district;
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
}
