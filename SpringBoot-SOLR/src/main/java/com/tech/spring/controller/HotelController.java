package com.tech.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.spring.model.Hotel;
import com.tech.spring.service.HotelService;

@RestController
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PostMapping("/hotel/save")
	public void addHotel(@RequestBody Hotel hotel) {
		hotelService.saveHotelDetails(hotel);
	}
	
//	@GetMapping("/{id}")
//    public Optional<Hotel> getHotel(@PathVariable String id) {
//        return hotelService.findById(id);
//    }

	/*
	 * @GetMapping("/hotel/suggetions") public void
	 * getHotelByName(@RequestParam("hotelName") String hotelName) {
	 * hotelService.searchByName(hotelName); }
	 */
}
