package com.mohamedhalith;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
//import test.cabapp.*;


public class Booking {

	public static void main(String[] args) {

		// Variables used in the program
		int choice;
		double kms, cost, tax;
		long mobile;
		LocalDate Date,DOB, cardValidity;
		LocalTime Time;
		LocalDateTime timestamp;
		String  password,dOB, date, time, option, validity;
		boolean output,seniorCitizen,doB,validCard;

		welcome();
		
		//Get user name and password from user
		Scanner input = new Scanner(System.in);
		//TestUserCredentials.testCases();
		System.out.println("Mobile No:");
		mobile = input.nextLong();
		System.out.println("Password");
		password=input.next();
		output = CredentialValidator.authenticate(password, mobile);
		if(output == false) {
			System.exit(1);
		}
		String[] cabs = { "Micro", "Mini", "Prime" };
		int[] price = { 10, 15, 20 };

		cabDetails(cabs, price);

		System.out.println("Enter your choice");
		choice = input.nextInt();
		while(choice>3 || choice<=0) {
			System.out.println("Please enter correct Choice");
			choice  = input.nextInt();
		}
		System.out.println("Enter Pickup Location");
		String pickup = input.next();
		System.out.println("Enter Destination");
		String destination = input.next();
		System.out.println("Enter No. of Kms");
		kms = input.nextInt();
		//TestBookingDateAndTime.testCases();
		System.out.println("Enter the journey date");
		date = input.next();
		Date = LocalDate.parse(date);
		System.out.println("Enter the travelling time");
		time = input.next();
		Time = LocalTime.parse(time);
		cost = PriceCalculator.calculate(price, kms, choice);

		// Finds whether the booking is made for peak hour
		
		cost = DateTimeValidator.peakHours(Time, cost);
		tax = PriceCalculator.calculateGST(cost);

		timestamp = LocalDateTime.now();

		System.out.println("Enter Your Date Of Birth(YYYY-MM-DD)");
		dOB = input.next();
		DOB = LocalDate.parse(dOB);
		doB =DateTimeValidator.validateDoB(DOB);
		if(doB==false)
			System.exit(1);
		seniorCitizen = DateTimeValidator.isSeniorCitizen(DOB);
		cost = Math.ceil(cost);
		printReceipt(cost, tax, kms, choice, Date, Time, timestamp, pickup, seniorCitizen, destination, mobile, cabs);
		//Assuming user is paying through card
		System.out.println("Would You like to pay through card?\nYes/No");
		option = input.next();
		if (option.equalsIgnoreCase("Yes")) {
			//To continue...................
			System.out.println("Enter card expiry year and month");
			validity = input.next();
			validity = validity + "-01";
			cardValidity = LocalDate.parse(validity);
			//Checks validity of Card
			validCard=DateTimeValidator.isCardValid(cardValidity);
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
		System.out.println("\n---------CAB DETAILS--------");
		for (int i = 0; i < cabs.length; i++) {
			System.out.println((i + 1) + ". " + cabs[i] + " -(Rs." + price[i] + "/km)");
		}
		System.out.println("Enter your choice (1/2/3)");
	}


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