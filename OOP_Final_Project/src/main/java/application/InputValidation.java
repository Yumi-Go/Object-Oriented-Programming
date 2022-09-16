package application;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class InputValidation {

	/** input validation commonly used in tab 1 and tab 2 */

	public static boolean isNumeric(String inputText) {
		ParsePosition parsePos = new ParsePosition(0);
        NumberFormat.getInstance().parse(inputText, parsePos);
        return inputText.length() == parsePos.getIndex();
	}
	
	
	
	/** tab1 input validation */
	
	public boolean tab1_isInputValid(String firstName, String middleName, String lastName, String id, String email, String phoneNumber, LocalDate dob) {
	
		// Required fields: firstName, lastName, studentID
		boolean allInputOK = false;
		if (firstName == null || firstName.length() == 0 || lastName == null || lastName.length() == 0) {
			Alert nullNameWarning = new Alert(AlertType.INFORMATION);
			nullNameWarning.setTitle("null Name");
			nullNameWarning.setHeaderText(null);
			nullNameWarning.setContentText("First name and Last name are required!");
			nullNameWarning.showAndWait();
		} else {
			if (id == null || id.length() == 0) {
				Alert nullIDWarning = new Alert(AlertType.INFORMATION);
				nullIDWarning.setTitle("null ID");
				nullIDWarning.setHeaderText(null);
				nullIDWarning.setContentText("Enter the Student ID!");
				nullIDWarning.showAndWait();
			} else {
				if (tab1_isStudentNameValid(firstName) == true && tab1_isStudentNameValid(middleName) == true 
						&& tab1_isStudentNameValid(lastName) == true && tab1_isStudentIdValid(id) == true 
						&& tab1_isEmailAddressValid(email) == true && tab1_isPhoneNumberValid(phoneNumber) == true)
        	 	{
        			allInputOK = true;
        	 	}
			}
		}
		
		return allInputOK;
		
	}

	
    private static boolean tab1_isStudentNameValid(String name) {
    	boolean nameOK = false;
    	boolean hasDigit = name.matches(".*\\d+.*");
    	if (hasDigit == true) {
    		Alert ifHasDigit = new Alert(AlertType.INFORMATION);
    		ifHasDigit.setTitle("Input Error");
    		ifHasDigit.setHeaderText(null);
    		ifHasDigit.setContentText("Student Name can not contain numbers!");
    		ifHasDigit.showAndWait();
    	} else {
    		nameOK = true;
    	}
    	return nameOK;
    }

    
    private static boolean tab1_isStudentIdValid(String id) {
    	boolean idOK = false;
    	try {
    		if (isNumeric(id) != true) {
    			Alert ifString = new Alert(AlertType.INFORMATION);
    			ifString.setTitle("Input Error");
    			ifString.setHeaderText(null);
    			ifString.setContentText("Only numbers are valid!");
    			ifString.showAndWait();
    		} else {
    			if (id.length() == 6) {
    				idOK = true;
                } else {
                	Alert wrongLengthID = new Alert(AlertType.INFORMATION);
                	wrongLengthID.setTitle("Input Error");
                	wrongLengthID.setHeaderText(null);
                	wrongLengthID.setContentText("Enter the 6-digit number for a Student ID!");
                	wrongLengthID.showAndWait();
                }
    		}
    	} catch (NumberFormatException e) {
    		System.out.println("Error!");
    	}
    	return idOK;
    }
    
    
    private static boolean tab1_isEmailAddressValid(String email) {
    	boolean emailOK = false;
    	String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";  
        Pattern pattern = Pattern.compile(regex);
        Matcher matchCheck = pattern.matcher(email);
           if (matchCheck.matches() == false && !(email == null || email.equals("null") || email.length() == 0)) {
                 Alert emailPatternNotMatch = new Alert(AlertType.INFORMATION);
                 emailPatternNotMatch.setTitle("Wrong E-mail Address");
                 emailPatternNotMatch.setHeaderText(null);
                 emailPatternNotMatch.setContentText("Enter the correct E-mail Address!");
                 emailPatternNotMatch.showAndWait();
           } else {
              emailOK = true;
           }
           return emailOK;
	}
    
    
    private static boolean tab1_isPhoneNumberValid(String phoneNumber) {
    	boolean phoneOK = false;
    	if (isNumeric(phoneNumber) != true && !(phoneNumber == null || phoneNumber.equals("null") || phoneNumber.length() == 0)) {
    		Alert ifString = new Alert(AlertType.INFORMATION);
    		ifString.setTitle("Input Error");
    		ifString.setHeaderText(null);
    		ifString.setContentText("Only numbers are valid for Phone Number!");
    		ifString.showAndWait();
    	} else {
    		phoneOK = true;
    	}

    	return phoneOK;
    }
    

    
	/** tab2 input validation */
    
	public static boolean tab2_isInputValid(Integer id, String module, String strGrade) {
		
		// Required fields: studentID, module
		boolean allInputOK = false;
		if (id == null || id.toString().length() == 0) { // if studentID is not selected from combobox
			Alert nullIDWarning = new Alert(AlertType.INFORMATION);
			nullIDWarning.setTitle("null ID");
			nullIDWarning.setHeaderText(null);
			nullIDWarning.setContentText("Select the Student ID!");
			nullIDWarning.showAndWait();
		} else {
			if (module == null || module.length() == 0) {
				Alert nullModuleWarning = new Alert(AlertType.INFORMATION);
				nullModuleWarning.setTitle("null Module Name");
				nullModuleWarning.setHeaderText(null);
				nullModuleWarning.setContentText("Enter the Module Name!");
				nullModuleWarning.showAndWait();				
			} else {
				if (tab2_isGradeValid(strGrade) == true)
        	 	{
        			allInputOK = true;
        	 	}
			}
		}
		
		return allInputOK;
		
	}
    
    
    public static boolean tab2_isGradeValid(String strGrade) {
    	boolean gradeOK = false;
    	try {
    		if (isNumeric(strGrade) != true) {
    			Alert ifString = new Alert(AlertType.INFORMATION);
    			ifString.setTitle("Input Error");
    			ifString.setHeaderText(null);
    			ifString.setContentText("Only numbers are valid!");
    			ifString.showAndWait();
    		} else {
    			if (!(strGrade == null || strGrade.equals("null") || strGrade.length() == 0)) {
    				Integer grade = Integer.parseInt(strGrade);
    				if (!(grade >= 0 && grade <= 100)) {
    	    			Alert wrongGrade = new Alert(AlertType.INFORMATION);
    	    			wrongGrade.setTitle("Input Error");
    	    			wrongGrade.setHeaderText(null);
    	    			wrongGrade.setContentText("Grade should be between 0 and 100!");
    	    			wrongGrade.showAndWait();
    				} else {
    					gradeOK = true;
    				}
                } else { // allow null value for grade
                	gradeOK = true;
                }
    		}
    	} catch (NumberFormatException e) {
    		System.out.println("Error!");
    	}
    	return gradeOK;
    }
    
    
    
}