package com.satyendra.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satyendra.flightreservation.entities.Passenger;
import com.satyendra.flightreservation.entities.User;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
