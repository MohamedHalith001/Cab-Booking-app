package com.mohamedhalith;

public class CredentialValidator {
	/**
	 * It checks the mobile number and password and validates whether it is valid or
	 * not. It also compares password to the default password and return a boolean
	 * value depending upon the result.
	 * 
	 * @param mobile
	 * @param defpwd
	 * @param password
	 * @param mobileNumber
	 * @return boolean
	 */
	public static boolean authenticate(String password, long mobileNumber) {
		Long mobile = 9524896959L;
		String defaultPassword = "pass1234";
		
		String mob = String.valueOf(mobile);
		if (mob.length() == 10 && mobileNumber == mobile) {
			if (password.length() >= 8) {
				if (password.equals(defaultPassword)) {
					return true;
				}
			}
		}
		return false;
	}
}
