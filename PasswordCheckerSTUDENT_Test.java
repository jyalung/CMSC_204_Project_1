package password;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This is your test file.  Complete the following test cases to test your project where they make sense.
 * Include additional test cases if you like.  
 *
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerSTUDENT_Test {
	ArrayList<String> password1;
	ArrayList<String> password2;
	String shortPassword = "short";
	String longPassword = "longpassword";
	String noUpperCase = "upper";
	String hasUpperCase = "upPer";
	String noLowerCase = "LOWER";
	String hasLowerCase = "LoWEr";
	String weak = "weaK";
	String strong = "SososoSoStrong";
	String validSquencePassword = "11223456789";
	String invalidSquencePassword = "1112223456789";
	String noDigitPassword = "onetwothree";
	String hasDigitPassword = "12345678910";
	String testPassword1 = "PassWord!SGood2";
	String testPassword2 = "IsGoodpassword1992!";

	@Before
	public void setUp() throws Exception {
		String[] containsInvalidPwd = {"BadPasswordOne", "b@dpassword2", "Badpassword3"};
		password1 = new ArrayList<String>();
		password1.addAll(Arrays.asList(containsInvalidPwd));		
		
		String[] allValidPasswords = {"Goodpassword#1", "@notherGoodPassw0rd", "wowGr3atPa$$words"};
		password2 = new ArrayList<String>();
		password2.addAll(Arrays.asList(allValidPasswords));	
		
	}

	@After
	public void tearDown() throws Exception {
		password1 = null;
		password2= null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidLength(longPassword));
		} 
		catch (LengthException e) {
			fail("This test shouldn't have failed!");
		}
		try {
			assertTrue(PasswordCheckerUtility.isValidLength(shortPassword));
		} 
		catch (LengthException e) {
			System.out.println("This test failed as expected!");
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha(hasUpperCase));
		} 
		catch (NoUpperAlphaException e) {
			fail("This test shouldn't have failed!");
		}
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha(noUpperCase));
		} 
		catch (NoUpperAlphaException e) {
			System.out.println("This test failed as expected!");
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha(hasLowerCase));
		} 
		catch (NoLowerAlphaException e) {
			fail("This test shouldn't have failed!");
		}
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha(noLowerCase));
		} 
		catch (NoLowerAlphaException e) {
			System.out.println("This test failed as expected!");
		}
	}
	/**
	 * Test if the password has between 6-9 characters
	 * This test should throw a WeakPasswordException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			assertFalse(PasswordCheckerUtility.isWeakPassword(strong));
		} 
		catch (WeakPasswordException e) {
			fail("This test shouldn't have failed!");
		}
		try {
			assertFalse(PasswordCheckerUtility.isWeakPassword(weak));
		} 
		catch (WeakPasswordException e) {
			System.out.println("This test failed as expected!");
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasSameCharInSequence(validSquencePassword));
		} 
		catch (InvalidSequenceException e) {
			fail("This test shouldn't have failed!");
		}
		try {
			assertTrue(PasswordCheckerUtility.hasSameCharInSequence(invalidSquencePassword));
		} 
		catch (InvalidSequenceException e) {
			System.out.println("This test failed as expected!");
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasDigit(hasDigitPassword));
		} 
		catch (NoDigitException e) {
			fail("This test shouldn't have failed!");
		}
		try {
			assertTrue(PasswordCheckerUtility.hasDigit(noDigitPassword));
		} 
		catch (NoDigitException e) {
			System.out.println("This test failed as expected!");
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword(testPassword1));
		} 
		catch (Exception e) {
			fail("This test shouldn't have failed!");
		}
		try {
				assertTrue(PasswordCheckerUtility.isValidPassword(testPassword2));
			} 
		catch (Exception e) {
			fail("This test shouldn't have failed!");
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(password1);
		assertEquals(results.size(), 3);
		assertEquals(results.get(0), "BadPasswordOne -> The password must contain at least one digit");
		assertEquals(results.get(1), "b@dpassword2 -> The password must contain at least one uppercase alphabetic character");
		assertEquals(results.get(2), "Badpassword3 -> The password must contain at least one special character");
		
		ArrayList<String> results2;
		results2 = PasswordCheckerUtility.getInvalidPasswords(password2);
		assertEquals(results2.size(), 0);
		

	}
	
}