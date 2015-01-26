package net.stefankrause.sj;

import java.util.HashMap;

import javax.faces.webapp.FacesServlet;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.sun.faces.config.ConfigureListener;

/** from https://github.com/Zergleb/Spring-Boot-JSF-Example */
@SpringBootApplication
public class SpringJsfEvaluationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJsfEvaluationApplication.class, args);
    }
    
//	@Bean
//	public static ViewScope viewScope() {
//		return new ViewScope();
//	}
//
//	/**
//	 * Allows the use of @Scope("view") on Spring @Component, @Service and @Controller
//	 * beans
//	 */
//	@Bean
//	public static CustomScopeConfigurer scopeConfigurer() {
//		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
//		HashMap<String, Object> hashMap = new HashMap<String, Object>();
//		hashMap.put("view", viewScope());
//		System.out.println("view scope registered");
//		configurer.setScopes(hashMap);
//		return configurer;
//	}

	@Bean
	public FacesServlet facesServlet() {
		return new FacesServlet();
	}

	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(
				facesServlet(), "*.xhtml");
		registration.setName("FacesServlet");
		registration.setLoadOnStartup(1);
		return registration;
	}

//	MyFaces	
//	@Bean
//	public ServletContextListener jsfConfigureListener() {
//		System.out.println("jsfConfigureListener");
//		return new StartupServletContextListener();
//	}   
	
//	Mojarra	
	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(
				new ConfigureListener());
	}    

}
