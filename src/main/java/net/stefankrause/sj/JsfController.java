package net.stefankrause.sj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import net.stefankrause.sj.edit.domain.Haustier;
import net.stefankrause.sj.edit.domain.HaustierInfo;
import net.stefankrause.sj.edit.domain.Schuhinfo;
import net.stefankrause.sj.edit.domain.Variante;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("session")
public class JsfController {
	
	private Schuhinfo selectedschuhinfo = Schuhinfo.Keine_Fuesse;
	
	public Schuhinfo getSelectedschuhinfo() {
		System.out.println("getSelectedschuhinfo "+selectedschuhinfo);
		return selectedschuhinfo;
	}

	public void setSelectedschuhinfo(Schuhinfo selectedschuhinfo) {
		System.out.println("setSelectedschuhinfo "+selectedschuhinfo);
		this.selectedschuhinfo = selectedschuhinfo;
	}
	
    private final static Schuhinfo[] schuhinfoArray = new Schuhinfo[] {
    	Schuhinfo.Unbekannt,
    	Schuhinfo.Bekannt,
    	Schuhinfo.Keine_Fuesse,
    };

    private Map<String, Haustier> haustierMap = new LinkedHashMap<>();
    
    
	private Collection<Schuhinfo> schuhinfos;
	private Collection<Haustier> haustiere;
	private Collection<Variante> varianten;

	public JsfController() {
		System.out.println("Hello JsfController");
	}

    @PostConstruct
    private void init() {
    	haustierMap.put("0", new Haustier("0", "Kein", 0));
    	haustierMap.put("1", new Haustier("1", "Maus", 5));
    	haustierMap.put("2", new Haustier("2", "Meerschwein", 10));
    	haustierMap.put("3", new Haustier("3", "Katze", 50));
    	haustierMap.put("4", new Haustier("4", "Hund", 1000));
    	
    	createDefaultForm();
    	System.out.println("init");
    }
    
	private Variante createDefaultVariante() {
		Variante ret = new Variante();
		ret.setSchuhinfo(Schuhinfo.Unbekannt);
		ret.setHaustiere(Collections.singletonList(new HaustierInfo(haustierMap.get("0").getId(), 1, 0)));
		return ret;
	}
	
	private HaustierInfo berechneHaustier(HaustierInfo hi) {
		HaustierInfo ret = new HaustierInfo();
		ret.setAnzahl(hi.getAnzahl());
		ret.setHaustierId(hi.getHaustierId());
		int kosten = hi.getAnzahl() * haustierMap.get(hi.getHaustierId()).getKosten();
		ret.setKosten(kosten);
		return ret;
	}
	
    private void createDefaultForm() {
    	this.setSchuhinfos(Arrays.asList(schuhinfoArray));
    	this.setHaustiere(haustierMap.values());
    	
    	ArrayList<Variante> varianten = new ArrayList<>();
    	varianten.add(createDefaultVariante());
    	varianten.add(createDefaultVariante());
    	varianten.add(createDefaultVariante());
    	this.setVarianten(varianten);
	}
	public Collection<Schuhinfo> getSchuhinfos() {
		return schuhinfos;
	}
	public void setSchuhinfos(Collection<Schuhinfo> schuhinfos) {
		this.schuhinfos = schuhinfos;
	}
	public Collection<Haustier> getHaustiere() {
		return haustiere;
	}
	public void setHaustiere(Collection<Haustier> haustiere) {
		this.haustiere = haustiere;
	}
	public Collection<Variante> getVarianten() {
		return varianten;
	}
	public void setVarianten(Collection<Variante> varianten) {
		this.varianten = varianten;
	}
	
	// 
	
	public void selectSchuh(Variante v) {
		System.out.println(v);
		updateSchuh(v); 
		System.out.println("selectSchuh "+v.getSchuhinfo()+" "+v.getSchuhanzahl()+" "+v.getSchuhpreis());
	}
	
	private void updateSchuh(Variante v) {
		if (v.getSchuhinfo() == Schuhinfo.Bekannt) {
			v.setSchuhpreis(v.getSchuhanzahl() * 89);
		} else {
			v.setSchuhanzahl(0);
			v.setSchuhpreis(0);
		}
	}

}
