package com.tech.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.spring.model.Hotel;
import com.tech.spring.repository.HotelRepository;

@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@SuppressWarnings("unused")
	public void saveHotelDetails(Hotel hotel) {
		hotelRepository.save(hotel, null);
	}

	/*
	 * public void searchByName(String name) { return
	 * hotelRepository.findByHotelName(name); }
	 */
	
}
