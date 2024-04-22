package com.satyendra.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyendra.flightreservation.dto.ReservationUpdateRequest;
import com.satyendra.flightreservation.entities.Reservation;
import com.satyendra.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRESTController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ReservationRESTController.class);
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation() with Id:"+id);
		Optional<Reservation> reservation1 =reservationRepository.findById(id);
		Reservation reservation=reservation1.get();
		return reservation;
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation() with request:"+ request);
		Optional<Reservation> reservation1=reservationRepository.findById(request.getId());
		Reservation reservation=reservation1.get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		return reservationRepository.save(reservation);
	}

}
