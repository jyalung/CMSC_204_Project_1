package password;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.text.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class PasswordMain extends BorderPane
{
	private Label passwordLabel, passwordALabel, instruction0, instruction1,instruction2,instruction3;
	private Label instruction4, instruction5,instruction6;
	private TextField pText1a, pText2a ;
	private TextField pText1b, pText2b ;
	private ArrayList<String> iPassword;
	private Button cpButton, eButton, cpfButton;
	DecimalFormat format = new DecimalFormat("#0.000");
	
	public PasswordMain()
	{
		iPassword = new ArrayList<String>();
		
		VBox subpanel = new VBox();
		instruction0 = new Label("Use the following rules when creating your passwords:");
		instruction1 = new Label("\t1.  Length must be greater than 6; a strong password will contain at least 10 characters");
		instruction2 = new Label("\t2.  Must contain at least one upper case alpha character");
		instruction3 = new Label("\t3.  Must contain at least one lower case alpha character");
		instruction4 = new Label("\t4.  Must contain at least one numeric charcter");
		instruction5 = new Label("\t5.  Must contain at least one special charcter");
		instruction6 = new Label("\t6.  May not have more than 2 of the same character in sequence");
		VBox.setMargin(instruction0, new Insets(2,10,2,10));
		VBox.setMargin(instruction1, new Insets(2,10,2,10));
		VBox.setMargin(instruction2, new Insets(2,10,2,10));
		VBox.setMargin(instruction3, new Insets(2,10,2,10));
		VBox.setMargin(instruction4, new Insets(2,10,2,10));
		VBox.setMargin(instruction5, new Insets(2,10,2,10));
		VBox.setMargin(instruction6, new Insets(2,10,2,10));

		subpanel.setAlignment(Pos.CENTER_LEFT);
		subpanel.getChildren().addAll(instruction0, instruction1, instruction2,
				instruction3, instruction4,instruction5, instruction6);
		
		HBox subpanel1a = new HBox();
		passwordLabel = new Label ("Password");
		pText1a = new TextField();
		pText1b = new TextField();
		HBox.setMargin(passwordLabel, new Insets(10,10,10,10));
		HBox.setMargin(pText1a, new Insets(10,10,10,10));
		subpanel1a.setAlignment(Pos.CENTER);
		subpanel1a.getChildren().addAll(passwordLabel, pText1a);
		
		HBox subpanel1b = new HBox();
		passwordALabel = new Label ("Re-type\nPassword");
		pText2a = new TextField();
		pText2b = new TextField();
		HBox.setMargin(passwordALabel, new Insets(10,10,10,10));
		HBox.setMargin(pText2a, new Insets(10,10,10,10));
		subpanel1b.setAlignment(Pos.CENTER);
		subpanel1b.getChildren().addAll(passwordALabel, pText2a);
		
		VBox subpanel1 = new VBox();
		VBox.setMargin(subpanel1a, new Insets(10,10,10,10));
		VBox.setMargin(subpanel1b, new Insets(10,10,10,10));
		subpanel1.setAlignment(Pos.CENTER);
		subpanel1.getChildren().addAll(subpanel1a, subpanel1b);
				
		cpfButton = new Button("Check Passwords in File");
		cpfButton.setMnemonicParsing(true); 
		cpfButton.setTooltip(new Tooltip("Select to read passwords from a file"));
		cpfButton.setOnAction(
        		event -> {
        			try {
						readFile();
					} catch (Exception e) {
						e.printStackTrace();
					}
        		});
		
		cpButton = new Button ("Check Password");
		cpButton.setMnemonicParsing(true); 
		cpButton.setTooltip(new Tooltip("Select to check a password."));
		cpButton.setOnAction(
        		event -> {
        			addPassword();
        		});
		
		eButton = new Button("Exit");
	    eButton.setMnemonicParsing(true);  
	    eButton.setTooltip(new Tooltip("Select to close the application"));
	    eButton.setOnAction(
        		event -> {
	            	 Platform.exit();
	                 System.exit(0);
        		}
        	);
		 
	
		HBox buttonPanel = new HBox(); //will format the three buttons for checking passwords, checking password from txt file and exit from the program.
		HBox.setMargin(cpButton, new Insets(10,10,10,10));
		HBox.setMargin(cpfButton, new Insets(10,10,10,10));
		HBox.setMargin(eButton, new Insets(10,10,10,10));
		buttonPanel.setAlignment(Pos.CENTER);
		buttonPanel.getChildren().addAll(cpButton, cpfButton, eButton);

		setTop(subpanel);
		setCenter(subpanel1);
		setBottom(buttonPanel);

	
	}

	public void addPassword() {
		
		String pString1 = pText1a.getText();
		String pString2 = pText2a.getText();
		try
		{
			if (pString1.compareTo(pString2) != 0)
				throw new UnmatchedException();
			if (PasswordCheckerUtility.isValidPassword(pString1)) {
				if (PasswordCheckerUtility.isWeakPassword(pString1)) {
					//checks that the password's length is between 6 to 9 characters
					JOptionPane.showMessageDialog(null, "Password is OK but weak", "Password Status", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Password is valid", "Password Status", JOptionPane.PLAIN_MESSAGE);
					pText1a.clear();
					pText2a.clear();
				}	
			}
		}
		catch (UnmatchedException ex) // will check if the password and the retyped password have the same characters
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Password Status", JOptionPane.PLAIN_MESSAGE);
			pText1a.clear();
			pText2a.clear();
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Password Error", JOptionPane.PLAIN_MESSAGE);
			pText1a.clear();
			pText2a.clear();
		}			
	}

	private class displayIllegalPasswords implements ActionListener 
	{
		public void actionPerformed (ActionEvent theEvent)
		{
			String result = "";
			for(String temp : iPassword)
				result += temp + "\n";
			
			JOptionPane.showMessageDialog(null, result, "Invalid Passwords", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void readFile() throws LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, NoSpecialCharacterException, Exception {
			FileChooser filechooser = new FileChooser();
			Scanner input;
			File selected = filechooser.showOpenDialog(null);
				if(selected != null)
			   {
				ArrayList<String> passwords = new ArrayList<String>();
				String results = "";
				try{
				input = new Scanner(selected);
				
				
				while(input.hasNext())
				{
					passwords.add(input.next());
				}
				iPassword = PasswordCheckerUtility.getInvalidPasswords(passwords);
				for(String pString1 : iPassword)
					results += pString1 +"\n";				
				
				JOptionPane.showMessageDialog(null, results, "Invalid Passwords", JOptionPane.PLAIN_MESSAGE);
			}
				catch(FileNotFoundException ex)
				{
					JOptionPane.showMessageDialog(null, ex.toString(), "File Error", JOptionPane.PLAIN_MESSAGE);
					ex.printStackTrace();
				}
			}
		}
}