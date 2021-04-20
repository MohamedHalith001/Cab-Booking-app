package com.mohamedhalith;


public class Calculation {
	/**
	 * Calculates cost excluding tax
	 * 
	 * @param price
	 * @param kms
	 * @param choice
	 * @return cost
	 */
	public static double calculate(int[] price, double kms, int choice) {
		double cost = price[choice - 1] * kms;
		return cost;
	}
	
	/**
	 * Calculates tax for the bill amount
	 * 
	 * @param cost
	 * @return tax
	 */
	public static double calculateGST(double cost) {
		double tax = cost * 0.07;
		return tax;
	}
	
	
}
