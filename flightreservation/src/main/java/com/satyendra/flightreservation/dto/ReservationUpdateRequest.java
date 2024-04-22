package com.satyendra.flightreservation.dto;

public class ReservationUpdateRequest {
	
	private Long id;
	private Boolean checkedIn;
	private int numberOfBags;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getCheckedIn() {
		return checkedIn;
	}
	public void setCheckedIn(Boolean chechedIn) {
		this.checkedIn = chechedIn;
	}
	public int getNumberOfBags() {
		return numberOfBags;
	}
	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

}
