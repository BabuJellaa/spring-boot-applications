package com.tech.spring.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import jakarta.persistence.Id;

@SolrDocument(collection = "hoteldata")
public class Hotel {
	@Field
	@Indexed(type = "string")
	private String hotelName;
	@Field
	@Id
	@Indexed(type = "string")
	private String hotelId;
	@Field
	@Indexed(type = "string")
	private String hotelCity;
	@Field
	@Indexed(type = "string")
	private String hotelState;
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelCity() {
		return hotelCity;
	}
	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}
	public String getHotelState() {
		return hotelState;
	}
	public void setHotelState(String hotelState) {
		this.hotelState = hotelState;
	}
}
