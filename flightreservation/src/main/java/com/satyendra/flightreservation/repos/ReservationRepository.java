package com.satyendra.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satyendra.flightreservation.entities.Reservation;
import com.satyendra.flightreservation.entities.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
