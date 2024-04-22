package com.satyendra.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.satyendra.flightreservation.dto.ReservationRequest;
import com.satyendra.flightreservation.entities.Flight;
import com.satyendra.flightreservation.entities.Reservation;
import com.satyendra.flightreservation.repos.FlightRepository;
import com.satyendra.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId,ModelMap modelMap) {
		LOGGER.info("Inside showCompleteResrvation()"+"FlightId" + flightId);
		Optional<Flight> flight1=flightRepository.findById(flightId);
		Flight flight=flight1.get();
		modelMap.addAttribute("flight",flight);
		return "completeReservation";	
	}
	
	@RequestMapping(value="/completeReservation",method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request,ModelMap modelMap) {
		LOGGER.info("Inside completeResrvation()"+"FlightId" + request);
		Reservation reservation=reservationService.bookFlight(request);
		modelMap.addAttribute("msg","Reservation created successfully and the id is "+reservation.getId());
		return "reservationConfirmation";
	}

}
