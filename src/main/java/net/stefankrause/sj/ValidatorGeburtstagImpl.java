package net.stefankrause.sj;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.inject.Named;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

/* Zitat JBOSS.org
 * https://docs.jboss.org/hibernate/validator/4.0.1/reference/en/html/validator-customconstraints.html
 * The implementation of the validator is straightforward. 
 * The initialize() method gives us access to the attribute values of the annotation to be validated. 
 * In the example we store the CaseMode in a field of the validator for further usage.
 */
public class ValidatorGeburtstagImpl implements ConstraintValidator<ValidatorGeburtstag,String>{
	public boolean isDatumValid(String datum) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate dateTime = LocalDate.parse(datum, formatter);
		if (dateTime.isAfter(LocalDate.now())) {
			return false;
		}
		return true;
	}

    @Override
    public void initialize(ValidatorGeburtstag arg0) {
        // IGNORE
    }

    @Override
    public boolean isValid(String datum, ConstraintValidatorContext arg1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateTime;
        try{
            dateTime = LocalDate.parse(datum, formatter);
        } catch(java.time.format.DateTimeParseException e){
            return false;
        }
        if (dateTime.isAfter(LocalDate.now())) {
            return false;
        }
        return true;
    }
}
