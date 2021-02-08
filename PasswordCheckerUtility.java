package password;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * A class which checks if a password is valid based on a list of 
 * rules, and throw exceptions if a given password breaks those rules.
 * These exceptions are named and return messages according/describing
 * which rules are being broken by the password.
 * @author Rowan Barr
*/
public class PasswordCheckerUtility {
	
	/** 
	 * Compares two passwords and throws an UnmatchedException if they 
	 * don't match.
	 * @param passOne The first password.
	 * @param passTwo The second password.
	*/
	public static void comparePasswords(String passOne, String passTwo) throws UnmatchedException
	{
		if (!passOne.equals(passTwo))
		{
			throw new UnmatchedException("Passwords do not match");
		}
	}
	
	/** 
	 * Compares two passwords and returns false if they don't 
	 * match.
	 * @param passOne The first password.
	 * @param passTwo The second password.
	 * @return Whether the passwords being equal is true or false. 
	*/
	public static boolean comparePasswordsWithReturn(String passOne, String passTwo)
	{
		if (passOne.equals(passTwo))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/** 
	 * Checks a password by every rule for password validity, returning
	 * true if it follows all the rules and throwing an exception
	 * when it breaks a rule.
	 * @param password A string password.
	*/
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException,
	NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
    {
        try {
            isValidLength(password);
        }
        catch (LengthException e) {
            throw (e);
        }
        
        try {
            hasUpperAlpha(password);
        }
        catch (NoUpperAlphaException e) {
        	throw (e);
        }
        
        try {
            hasLowerAlpha(password);
        }
        catch (NoLowerAlphaException e) {
        	throw (e);
        }
        
        try {
            hasDigit(password);
        }
        catch (NoDigitException e) {
        	throw (e);
        }
       
       
        try {
            hasSpecialChar(password);
        }
        catch (NoSpecialCharacterException e) {
        	throw (e);
        }
        try {
            hasSameCharInSequence(password);
        }
        
        catch (InvalidSequenceException e) {
        	throw (e);
        }
        return true;
    }
	
	/** 
	 * Checks a password to see if it is between 6 and 9 characters 
	 * and throws a WeakPasswordException if it does, otherwise 
	 * returns false.
	 * @param password A string password.
	 * @return Whether the password being weak is false.
	*/
	
    public static boolean isWeakPassword(String password) throws WeakPasswordException
    {
    	
        if(hasBetweenSixAndNineChars(password)) {
        	throw new WeakPasswordException("The password is ok but weak - it contains fewer than 10 characters.");
        }
        else
        	return false;
        
    }
    
    /** 
	 * Takes an ArrayList of passwords and creates a loop within which
	 * it checks each password to see if it breaks any rules.
	 * If a password does break a rule, it gets added to a new ArrayList
	 * of invalid passwords along with a description of the first 
	 * exception it threw, then it continues the loop without checking 
	 * if that specific password breaks any other rules.
	 * @param passwords An ArrayList of passwords.
	 * @return An ArrayList of invalid passwords.
	*/
    
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords)
    {
        ArrayList invalidPasswords = new ArrayList();
        for(int i = 0; i < passwords.size(); i++) 
        {
            try 
        	{
            	isValidLength(passwords.get(i));
            }
        	catch (LengthException e) 
        	{
        		invalidPasswords.add(passwords.get(i)+ " -> " + e.getMessage());
        		continue;
        	}
            
            try
            {
            	hasUpperAlpha(passwords.get(i));
            }
            catch (NoUpperAlphaException e)
            {
            	invalidPasswords.add(passwords.get(i)+ " -> " + e.getMessage());
            	continue;
            }
            try
            {
            	hasLowerAlpha(passwords.get(i));
            }
            catch (NoLowerAlphaException e)
            {
            	invalidPasswords.add(passwords.get(i)+ " -> " + e.getMessage());
            	continue;
            }
        	try 
        	{
        		hasDigit(passwords.get(i));
        	}
        	catch (NoDigitException e)
            {
        		invalidPasswords.add(passwords.get(i)+ " -> " + e.getMessage());
        		continue;
            }
           
            try
            {
            	hasSpecialChar(passwords.get(i));
            }
            catch (NoSpecialCharacterException e)
            {
            	invalidPasswords.add(passwords.get(i)+ " -> " + e.getMessage());
            	continue;
            }
            try
            {
            	hasSameCharInSequence(passwords.get(i));
            }
            catch (InvalidSequenceException e)
            {
            	invalidPasswords.add(passwords.get(i)+ " -> " + e.getMessage());
            	continue;
            }
        }
        
        return invalidPasswords;
            
    }
    
    /** 
	 * Checks a password to see if it is at least six characters and
	 * throws a LengthException if it is shorter than six characters.
	 * @param password A string password.
	 * @return Whether the password being at least six characters is true.
	*/
    
    public static boolean isValidLength(String password) throws LengthException
    {
        if (password.length() >= 6)
        {
            return true;
        }
        else
        {
            throw new LengthException("The password must be at least 6 characters long");
        }
    }
    
    /** 
	 * Checks a password to see if it is between 6 and 9 characters 
	 * and returns true if it does, otherwise returns false.
	 * @param password A string password.
	 * @return Whether the password having between 6 and 9 characters is true.
	*/
    public static boolean hasBetweenSixAndNineChars(String password) 
    {
        if (password.length() >=6 && password.length() <=9)
        {
        	return true;
        }
        else
        {
            return false;
        }
    }
    
    /** 
	 * Checks a password to see if it contains at least one digit and returns
	 * true if it does, otherwise throws NoDigitException.
	 * @param password A string password.
	 * @return Whether the password containing a digit is true.
	*/
    public static boolean hasDigit(String password) throws NoDigitException
    {
        for (int i = 0; i < password.length(); i++)
            if (password.charAt(i) > 47 && password.charAt(i) < 58)
            {
                return true;
            }
        throw new NoDigitException("The password must contain at least one digit");
    }
    
    /** 
	 * Checks a password to see if it contains at least one upper case letter
	 * and returns true if it does, otherwise throws NoUpperAlphaException.
	 * @param password A string password.
	 * @return Whether the password containing an upper case letter is true.
	*/
    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException
    {
    	for (int i = 0; i < password.length(); i++)
            if (password.charAt(i) > 64 && password.charAt(i) < 91)
            {
                return true;
            }
        throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
    }
    
    /** 
   	 * Checks a password to see if it contains at least one lower case letter
   	 * and returns true if it does, otherwise throws NoLowerAlphaException.
   	 * @param password A string password.
   	 * @return Whether the password containing an lower case letter is true.
   	*/
    public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException
    {
    	for (int i = 0; i < password.length(); i++)
    		if (password.charAt(i) > 96 && password.charAt(i) < 123)
    		{
                return true;
    		}
        throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
    }
    
    /** 
   	 * Checks a password to see if it contains at least one special character
   	 * and returns true if it does, otherwise throws NoSpecialCharacterException.
   	 * @param password A string password.
   	 * @return Whether the password containing a special character is true.
   	*/
    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException
    {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) 
        {
        	throw new NoSpecialCharacterException("The password must contain at least one special character");
        }
        else
        {
        	return true;
        }
    }
    
    /** 
   	 * Checks a password to see if it doesn't contain three of the same character
   	 * in a row and returns true if it doesn't, otherwise throws 
   	 * InvalidSequenceException.
   	 * @param password A string password.
   	 * @return Whether the password doesn't contain three of the same character
   	 * in a row.
   	*/
    public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException
    {
        for (int i = 2; i < password.length(); i++)
            if (password.charAt(i) == password.charAt(i-1))
                if (password.charAt(i) == password.charAt(i-2))
                {
                    throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
                }
        return true;
    }
    

}