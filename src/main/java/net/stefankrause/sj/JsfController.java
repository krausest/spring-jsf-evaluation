package net.stefankrause.sj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import net.stefankrause.sj.edit.domain.Haustier;
import net.stefankrause.sj.edit.domain.HaustierInfo;
import net.stefankrause.sj.edit.domain.Schuhinfo;
import net.stefankrause.sj.edit.domain.Variante;

import org.springframework.context.annotation.Scope;
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
	private List<Haustier> haustiere;
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
    
    public void reset() {
    	System.out.println("reset");
    	createDefaultForm();
    }
    
	private Variante createDefaultVariante() {
		Variante ret = new Variante();
		ret.setSchuhinfo(Schuhinfo.Unbekannt);
		ret.setHaustiere(new ArrayList<>());
		ret.getHaustiere().add(new HaustierInfo(haustierMap.get("0").getId(), 1, 0));
		ret.setShowInputElements(true);
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
    	this.setHaustiere(new ArrayList<>(haustierMap.values()));
    	
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
	public void setHaustiere(List<Haustier> haustiere) {
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
	
	public void addHaustier(Variante v) {
		System.out.println("addHaustier");
		v.getHaustiere().add(new HaustierInfo("0", 0, 0));
	}
	
	public boolean showAddButton(Variante v) {
		if (v.getHaustiere().size()+1 >= getHaustiere().size())
			return false;
		String last = v.getHaustiere().size() > 0 ? v.getHaustiere().get(v.getHaustiere().size()-1).getHaustierId() : null; 
		if ("0".equals(last)) {
			return false;
		}
		return true;
	}
	
	public List<Haustier> getErlaubteHaustiere(Variante v, HaustierInfo hi) {
		Set<String> selectedIds = v.getHaustiere().stream().map(h -> h.getHaustierId()).filter(s -> !s.equals(hi.getHaustierId())).collect(Collectors.toSet());
		ArrayList<Haustier> tiere = new ArrayList<Haustier>();
		for (int i=0;i<haustiere.size();i++) {
			if (i==0 || !selectedIds.contains(haustiere.get(i).getId())) {
				tiere.add(haustiere.get(i));
			}
		}
		return tiere;
	}
	
	public void updateHaustierKosten(Variante v, HaustierInfo hi) {
		if ("0".equals(hi.getHaustierId())) {
			v.getHaustiere().remove(hi);
		} else {
			hi.setKosten(hi.getAnzahl() * haustierMap.get(hi.getHaustierId()).getKosten());
		}
	}
	
	public void removeVariante(Variante v) {
		varianten.remove(v);
	}
	
	public String getColumnStyleClass() {
		return ""+12 / (varianten.size()+1);
	}
}
