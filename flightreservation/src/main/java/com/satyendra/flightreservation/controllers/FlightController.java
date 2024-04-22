package com.satyendra.flightreservation.controllers;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.satyendra.flightreservation.entities.Flight;
import com.satyendra.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(FlightController.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	
	@RequestMapping("findflights")
	public String findFlights(@RequestParam("from")String from,@RequestParam("to") String to,ModelMap modelMap) {
		LOGGER.info("Inside findFlights()"+"From:"+from+"To:"+to);
		List<Flight>flights= flightRepository.findFlights(from,to);
		modelMap.addAttribute("flights",flights);
		LOGGER.info("Flight found are:"+ flights);
		return "displayFlights";
	}
	
	@RequestMapping("admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
	}

}
