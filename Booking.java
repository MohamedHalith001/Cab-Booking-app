package com.mohamedhalith;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Booking {

	public static void main(String[] args) {

		// Variables used in the program
		int choice;
		double kms, cost, tax;
		long mobile, mob;
		LocalDate Date, current, DOB, valid;
		LocalTime Time, currTime;
		LocalDateTime timestamp;
		String defPwd, pwd, dOB, date, time, ch, validity;
		boolean output,senior,dateTime,doB,validCard;

		welcome();
		
		//Get user name and password from user
		Scanner input = new Scanner(System.in);
		System.out.println("Mobile No:     ");
		mob = 9524896959L;
		mobile = input.nextLong();
		defPwd = "pass1234";
		System.out.println("Password       ");
		pwd = input.next();
		output = CredentialValidator.authenticate(mobile, defPwd, pwd, mob);

		if (output == false) {
			System.err.println("Invalid Credentials");
			System.exit(1);
		}
		
		String[] cabs = { "Micro", "Mini", "Prime" };
		int[] price = { 10, 15, 20 };

		cabDetails(cabs, price);
		choice = input.nextInt();

		System.out.println("Enter your Pickup Location");
		String pickup = input.next();

		System.out.println("Enter your Destination");
		String destination = input.next();

		System.out.println("Please Enter the distance between your location and destination(in KM)");
		kms = input.nextDouble();

		System.out.println("Enter Journey Date (YYYY-MM-DD)");
		date = input.next();
		Date = LocalDate.parse(date);

		current = LocalDate.now();

		currTime = LocalTime.now();

		System.out.println("Enter Journey Time (HH:MM)");
		time = input.next();
		Time = LocalTime.parse(time);
		dateTime =DateTimeValidator.validateDateTime(Date, Time, current, currTime);
		if(dateTime==false)
			System.exit(1);

		cost = Calculation.calculate(price, kms, choice);

		// Finds whether the booking is made for peak hour
		
		cost = DateTimeValidator.peakHours(Time, cost);
		tax = Calculation.calculateGST(cost);

		timestamp = LocalDateTime.now();

		System.out.println("Enter Your Date Of Birth(YYYY-MM-DD)");
		dOB = input.next();
		DOB = LocalDate.parse(dOB);
		doB =DateTimeValidator.validateDoB(DOB, current);
		if(doB==false)
			System.exit(1);
		senior = DateTimeValidator.isSeniorCitizen(DOB,current);
		cost = Math.ceil(cost);
		
		printReceipt(cost, tax, kms, choice, Date, Time, timestamp, pickup, senior, destination, mobile, cabs);
		System.out.println("\n\nWould You like to pay through Card\n (Yes/No)");

		ch = input.next();
		if (ch.equalsIgnoreCase("Yes")) {
			System.out.println("Enter the Expiry Month and Year of the card (YYYY-MM-DD) (Please add 01 for DD)");
			validity = input.next();
			valid = LocalDate.parse(validity);
			//Checks validity of Card
			validCard=DateTimeValidator.isCardValid(valid, current);
			if(validCard) {
				System.out.println("Payment Successful :) ");
			}else {
				System.out.println("Payment Declined... Card Expired!!!");
				System.out.println("Try again with another card");
			}
		}
		System.out.println("\t\tThank you for using our service\tHave a good day :)");
		input.close();
	}
	/**
	 * Prints the welcome message to the customer
	 **/
	private static void welcome() {
		System.out.println("------------------------------------------------------------");
		System.out.println("\n\nWelcome to the Cab-Booking Service");
		System.out.println("------------------------------------------------------------");
	}
	/**
	 * This methods lists all the available types of services provided by the
	 * service provider. Thus, helps the user to choose a service from the list.
	 * 
	 * @param cabs
	 * @param price
	 */
	private static void cabDetails(String[] cabs, int[] price) {
		System.out.println("---------CAB DETAILS--------");
		for (int i = 0; i < cabs.length; i++) {
			System.out.println((i + 1) + ". " + cabs[i] + " -(Rs." + price[i] + "/km)");
		}
		System.out.println("Enter your choice (1/2/3)");
	}

	/**
	 * This method is used to print the invoice/bill to the customer for the service
	 * provided. It also checks whether the customer is valid for the Senior Citizen
	 * Discount and reduces the appropriate amount from the invoice.
	 * 
	 * @param cost
	 * @param tax
	 * @param kms
	 * @param choice
	 * @param date
	 * @param time
	 * @param timestamp
	 * @param pickup
	 * @param senior
	 * @param destination
	 * @param mobile
	 * @param cabs
	 */
	private static void printReceipt(double cost, double tax, double kms, int choice, LocalDate date, LocalTime time,
			LocalDateTime timestamp, String pickup, boolean senior, String destination, long mobile, String[] cabs) {
		System.out.println("------------------Receipt------------------");
		System.out.println("Pickup Location                       " + pickup);
		System.out.println("Destination                           " + destination);
		System.out.println("Contact No:                           " + mobile);
		System.out.println("No.of Kms booked                      " + kms);
		System.out.println("Journey Date                          " + date);
		System.out.println("Journey Time                          " + time);
		System.out.println("Type of Car                           " + cabs[choice - 1]);
		System.out.println("Cost for the journey                  " + cost);
		System.out.println("GST        7%                         " + String.format("%.5f", tax));
		System.out.println("Total                                 " + Math.ceil(cost + tax));
		if (senior)
			System.out.println("\nSenior Citizen Discount      50%      " + Math.ceil(cost / 2));
		System.out.println("\n\n**Additional Charges may have been applied based on hours\n");
		System.out.println("\n\nDate of Booking        " + timestamp);
	}

	
}