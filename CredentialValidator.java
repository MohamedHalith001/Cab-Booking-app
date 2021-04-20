package com.mohamedhalith;

public class CredentialValidator {
	/**
	 * It checks the mobile number and password and validates whether it is valid or
	 * not. It also compares password to the default password and return a boolean
	 * value depending upon the result.
	 * 
	 * @param mobile
	 * @param defpwd
	 * @param pwd
	 * @param mob2
	 * @return boolean
	 */
	public static boolean authenticate(long mobile, String defpwd, String pwd, long mob2) {
		String mob = String.valueOf(mobile);
		if (mob.length() == 10 && mob2 == mobile) {
			if (pwd.length() >= 8) {
				if (pwd.equals(defpwd)) {
					return true;
				}
			}
		}
		return false;
	}
}
