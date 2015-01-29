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
@Scope("view")
public class EinfachController {
	
	private PersonData personData;
	
	public EinfachController() {
		System.out.println("EinfachController");
		personData = new PersonData();
	}

	public PersonData getPersonData() {
		return personData;
	}

	public void setPersonData(PersonData personData) {
		this.personData = personData;
	}
	
	public String save() {
		System.out.println("save "+personData);
		return "secondPage.xhtml";
	}
	
	public String backPage1() {
		System.out.println("Back to Page 1 "+personData);
		return "einfach.xhtml";
	}

	public String savePage2() {
		System.out.println("Save page 2 "+personData);
		return null;
	}
	
}
