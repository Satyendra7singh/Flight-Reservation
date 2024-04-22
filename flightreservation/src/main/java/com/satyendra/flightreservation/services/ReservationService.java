package com.satyendra.flightreservation.services;

import com.satyendra.flightreservation.dto.ReservationRequest;
import com.satyendra.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
