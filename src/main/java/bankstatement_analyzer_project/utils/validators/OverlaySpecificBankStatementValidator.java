package bankstatement_analyzer_project.utils.validators;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import bankstatement_analyzer_project.domains.Notification;
import bankstatement_analyzer_project.utils.DateInTheFutureException;
import bankstatement_analyzer_project.utils.DescriptionTooLongException;
import bankstatement_analyzer_project.utils.InvalidAmountException;
import bankstatement_analyzer_project.utils.InvalidDateFormat;

public class OverlaySpecificBankStatementValidator {
    private String description;
    private String date;
    private String amount;
    
	public OverlaySpecificBankStatementValidator(String description, String date, String amount) {
		this.description = description;
		this.date = date;
		this.amount = amount;
	}
    
	public Notification validate() {
		final Notification notification = new Notification();
		
		if(this.description.length() > 100) {
			notification.addError("The description is too long");
		}
		
		final LocalDate parsedDate;
		try {
			parsedDate = LocalDate.parse(this.date);
			if(parsedDate.isAfter(LocalDate.now())) {
				notification.addError("date cannot be in the future");
			}
		} catch (DateTimeParseException e) {
			notification.addError("Invalid format for date");
		}
		
		final double amount;
		try {
			amount = Double.parseDouble(this.amount);
		} catch (NumberFormatException e) {
			notification.addError("Invalid format for amount");
		}
		
		return notification;
	}
    
}
