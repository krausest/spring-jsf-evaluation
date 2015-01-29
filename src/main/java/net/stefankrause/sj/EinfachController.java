package net.stefankrause.sj;

import java.text.NumberFormat;
import java.util.Locale;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import net.stefankrause.sj.einfach.domain.PersonData;

import org.springframework.context.annotation.Scope;

//@RestController
//@Scope("session")
@Named  
@Scope("session")
public class EinfachController {
	@Resource
	private GeburtsdatumValidatorService geburtsdatumValidatorService;
	
	@Resource 
	private TestBean testBean;
	
	public TestBean getTestBean(){
	    return  testBean;
	}
	
	private PersonData personData;
	
	public EinfachController() {
		init();
	}

	private void init() {
		personData = new PersonData();
	}
	
    public void reset() {
    	init();
    }

	public PersonData getPersonData() {
		return personData;
	}

	public void setPersonData(PersonData personData) {
		this.personData = personData;
	}
	
	public String save() {
	  if (!geburtsdatumValidatorService.isDatumValid(personData.getGeburtsdatum())) {
		  FacesContext.getCurrentInstance().addMessage("inpGeburtsdatum", new FacesMessage("Geburtsdatum muss in der Vergangenheit liegen"));
	      return null;
	  } else {
		return "finished.xhtml";
	  }
	}
	
	public void toggleErweitert(ValueChangeEvent ev) {
		personData.setErweitert((Boolean)ev.getNewValue());
		System.out.println("toggle erweitert "+personData.isErweitert());
	}
	
	public void validateNotEmptyIfErweitert(FacesContext ctx, UIComponent comp,
		    Object value) throws ValidatorException {
		  if (value instanceof String) {
			  String str = (String)value;
			  if ((str==null || str.trim().length()==0) && personData.isErweitert()) {
			      throw new ValidatorException(
			          new FacesMessage("messageText", null));
			  }
		  } else {
		      throw new IllegalStateException("Nur für Strings zulässig");
		  }
		}

	public void validateNotEmptyNumberIfErweitert(FacesContext ctx, UIComponent comp,
			Object value) throws ValidatorException {
		if (value instanceof String) {
			String str = (String)value;
			boolean failed = false;
			if (str==null || str.trim().length()==0) {
				failed = true;
			} else {
				try {
					NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
					nf.setMinimumFractionDigits(0);
					nf.setMaximumFractionDigits(0);
					nf.parse(str).intValue();
				} catch (Exception e) {
					failed = true;
				}
			}
			if (failed && personData.isErweitert()) {
				throw new ValidatorException(
						new FacesMessage("messageText", null));
			}
		} else {
			throw new IllegalStateException("Nur für Strings zulässig");
		}
	}
}
