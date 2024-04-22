package com.satyendra.flightreservation.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.satyendra.flightreservation.controllers.ReservationController;
import com.satyendra.flightreservation.dto.ReservationRequest;
import com.satyendra.flightreservation.entities.Flight;
import com.satyendra.flightreservation.entities.Passenger;
import com.satyendra.flightreservation.entities.Reservation;
import com.satyendra.flightreservation.repos.FlightRepository;
import com.satyendra.flightreservation.repos.PassengerRepository;
import com.satyendra.flightreservation.repos.ReservationRepository;
import com.satyendra.flightreservation.services.ReservationService;
import com.satyendra.flightreservation.util.EmailUtil;
import com.satyendra.flightreservation.util.PDFGenerator;

import jakarta.transaction.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.satyendra.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	private static final Logger LOGGER=LoggerFactory.getLogger(ReservationService.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		LOGGER.info("Inside bookFlight()");
		//Make Payment
		Long flightId=request.getFlightId();
		Optional<Flight> flight1=flightRepository.findById(flightId);
		Flight flight=flight1.get();
		
		Passenger passenger=new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		Passenger savedPassenger=passengerRepository.save(passenger);
		LOGGER.info("Saving the passenger:"+ passenger);
		
		Reservation reservation=new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		reservation.setNumberOfBags(1);
		
		Reservation savedReservation=reservationRepository.save(reservation);
		LOGGER.info("Saving the reservation:"+ reservation);
		
		String filePath = ITINERARY_DIR + savedReservation.getId()+ ".pdf";
		pdfGenerator.generateItinerary(savedReservation,filePath);
		//emailUtil.sendItinerary(passenger.getEmail(),filePath);
		LOGGER.info("Generating the Itinenary");
		
		return savedReservation;
	}

}
