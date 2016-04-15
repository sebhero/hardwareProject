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

	/**
	 * Returns lastname
	 * @return lastname
     */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets s new lastname
	 * @param lastName new lastname
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns firstname
	 * @return firstname
     */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets a new firstname
	 * @param firstName new firstname
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns if the user have checked in or out
	 * @return true = in,false = out
     */
	public boolean isCheckIn() {
		return checkIn;
	}

	/**
	 * Sets if the stamp is an in or out check
	 * @param checkIn
     */
	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

	/**
	 * Returns an dateobject
	 * @return
     */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Set the date to a new date
	 * @param date new date
     */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Makes all info from the class to an string
	 * @return a string with info
     */
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