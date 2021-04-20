package com.mohamedhalith;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeValidator {
	/**
	 * Checks whether the journey Date and Time are valid. If it is invalid, it
	 * gives an error message and stops the program.
	 * 
	 * @param date
	 * @param time
	 * @param current
	 * @param currTime
	 * @return 
	 */
	public static boolean validateDateTime(LocalDate date, LocalTime time, LocalDate current, LocalTime currTime) {
		// Validating the provided date and Time
		if (date.isBefore(current)) {
			return false;
		} else if (date.isEqual(current)) {

			if (time.isBefore(currTime)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method verifies the date of birth provided by the user. If the given
	 * data is correct,the program continues to book the cab, else an error message
	 * is sent and program is stopped
	 * 
	 * @param dOB
	 * @param current
	 * @return boolean
	 */
	public static boolean validateDoB(LocalDate dOB, LocalDate current) {
		// TODO Auto-generated method stub
		if (dOB.isAfter(current)) {
			return false;
		}
		if (dOB.isBefore(current)) {
			if ((current.getYear() - dOB.getYear()) < 5) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Redirecting user to the payment portal and verifying the payment mode details
	 * 
	 * @param valid
	 * @param current
	 * @return boolean
	 */
	public static boolean isCardValid(LocalDate valid, LocalDate current) {
		if (valid.isBefore(current)) {
			return false;
		} 
		return true;
	}
	
	/**
	 * Checks whether the user is eligible for senior-citizen discount and reduces
	 * bill amount respectively
	 * 
	 * @param dOB 
	 * @param current 
	 * @return boolean
	 */
	public static boolean isSeniorCitizen(LocalDate dOB, LocalDate current) {
		int age = current.getYear() - dOB.getYear();
		if (age >= 60)
			return true;
		return false;
	}
	/**
	 * Checks for the peak hour and adds amount accordingly.
	 * 
	 * @param time
	 * @param cost
	 * @return
	 */
	public static double peakHours(LocalTime time, double cost) {
		int hour = time.getHour();
		if (hour > 15 && hour < 19) {
			cost += (cost * 0.125);
		}
		return cost;
	}
}
