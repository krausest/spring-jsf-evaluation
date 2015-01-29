package net.stefankrause.sj;

import java.text.NumberFormat;
import java.util.Locale;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;

import net.stefankrause.sj.einfach.domain.PersonData;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Scope("session")
public class EinfachController {
	private GeburtsdatumValidatorService geburtsdatumValidatorService = new GeburtsdatumValidatorService();
//	@Resource
//	private GeburtsdatumValidatorService geburtsdatumValidatorService;
	
	private PersonData personData;
	
	private UIInput inpGeburtsdatum;
	
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
		System.out.println("save");
	  if (!geburtsdatumValidatorService.isDatumValid(personData.getGeburtsdatum())) {
		  System.out.println("save - Geburtsdatum invalid "+personData);
		  FacesContext.getCurrentInstance().addMessage(inpGeburtsdatum.getClientId(), new FacesMessage("Geburtsdatum muss in der Vergangenheit liegen"));
		  inpGeburtsdatum.setValid(false);
	      return null;
	  } else {
		  System.out.println("save - page is valid "+personData);
		return "secondPage.xhtml";
	  }
	}
	
	public String backPage1() {
		System.out.println("Back to Page 1 "+personData);
		return "einfach.xhtml";
	}

	public String savePage2() {
		System.out.println("Save page 2 "+personData);
		return null;
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

	public UIInput getInpGeburtsdatum() {
		return inpGeburtsdatum;
	}

	public void setInpGeburtsdatum(UIInput inpGeburtsdatum) {
		this.inpGeburtsdatum = inpGeburtsdatum;
	}
}
