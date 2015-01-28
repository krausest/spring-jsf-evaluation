package net.stefankrause.sj.einfach.domain;

import java.util.List;

public class PersonData {
	private String name;
	private String vorname;
	private String geburtsdatum;
	private boolean erweitert;
	private String straße;
	private String hausnummer;
	private String plz;
	private String ort;
	private List<String> errors;
	private String hobby;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	public boolean isErweitert() {
		return erweitert;
	}
	public void setErweitert(boolean erweitert) {
		this.erweitert = erweitert;
	}
	public String getStraße() {
		return straße;
	}
	public void setStraße(String straße) {
		this.straße = straße;
	}
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	@Override
	public String toString() {
		return "PersonData [name=" + name + ", vorname=" + vorname
				+ ", geburtsdatum=" + geburtsdatum + ", erweitert=" + erweitert
				+ ", straße=" + straße + ", hausnummer=" + hausnummer
				+ ", plz=" + plz + ", ort=" + ort + ", errors=" + errors+ ", hobby=" + hobby + "]";
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
}
