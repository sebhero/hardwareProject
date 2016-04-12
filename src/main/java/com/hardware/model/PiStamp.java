package com.hardware.model;

/**
 * Created by seb on 2016-04-12.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Calendar;


/***
 * Pistamp is used on the pi for dispalying info when a user stamps in.
 * this is the return model
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PiStamp implements Serializable {

	private String lastName;
	private String firstName;
	private boolean checkIn;
	private Calendar date;


	public PiStamp() {
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isCheckIn() {
		return checkIn;
	}

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PiStamp{" +
				"lastName='" + lastName + '\'' +
				", firstName='" + firstName + '\'' +
				", Time='" + this.getDate() + '\'' +
				", CheckIn='" + this.isCheckIn() + '\'' +
				'}';
	}
}