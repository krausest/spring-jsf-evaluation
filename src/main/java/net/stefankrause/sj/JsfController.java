package net.stefankrause.sj;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("session")
public class JsfController {
	private String jsfTestString = "Hier bin ich...";

	public JsfController() {
		System.out.println("Hello JsfController");
	}
	
	public String getJsfTestString() {
		return jsfTestString;
	}

	public void setJsfTestString(String jsfTestString) {
		this.jsfTestString = jsfTestString;
	}
}
