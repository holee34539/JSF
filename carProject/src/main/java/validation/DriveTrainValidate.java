package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("carProject.DriveTrainValidate")
public class DriveTrainValidate implements Validator {

	// Horsepower Pattern
	private static final String DT_PATTERN = "^.{1,254}$";
	
	private Pattern pattern;
	private Matcher matcher;

	public DriveTrainValidate() {
		pattern = Pattern.compile(DT_PATTERN);
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component,
					Object value) throws ValidatorException {
		
		matcher = pattern.matcher(value.toString());
		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("Drivetrain validation failed.",
					"Invalid Drivetrain Format");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
	
	public boolean checkFormat(String target) {
		matcher = pattern.matcher(target);
		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("Drivetrain validation failed.",
					"Invalid Drivetrain Format");
			sendMessage(msg);
			return false;
		}
		return true;
	}
	
	public void sendMessage(FacesMessage msg) {
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	}
	
}
