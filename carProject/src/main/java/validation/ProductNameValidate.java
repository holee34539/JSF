package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("carProject.ProductNameValidate")
public class ProductNameValidate implements Validator {

	// Horsepower Pattern
	private static final String PN_PATTERN = "^.{1,254}$";
	
	private Pattern pattern;
	private Matcher matcher;

	public ProductNameValidate() {
		pattern = Pattern.compile(PN_PATTERN);
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component,
					Object value) throws ValidatorException {
		
		matcher = pattern.matcher(value.toString());
		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("Product Name validation failed.",
					"Invalid Product Name Format");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
	
	public boolean checkFormat(String target) {
		matcher = pattern.matcher(target);
		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("Product Name validation failed.",
					"Invalid Product Name Format");
			sendMessage(msg);
			return false;
		}
		return true;
	}
	
	public void sendMessage(FacesMessage msg) {
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	}
	
}
