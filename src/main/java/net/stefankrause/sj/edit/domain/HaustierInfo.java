package net.stefankrause.sj.edit.domain;

public class HaustierInfo {
	
	private String haustierId;
	private int anzahl;
	private int kosten;
	
	public HaustierInfo() {
	}
	
	public HaustierInfo(String haustierId, int anzahl, int kosten) {
		super();
		this.haustierId = haustierId;
		this.anzahl = anzahl;
		this.kosten = kosten;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public int getKosten() {
		return kosten;
	}

	public void setKosten(int kosten) {
		this.kosten = kosten;
	}

	public String getHaustierId() {
		return haustierId;
	}

	public void setHaustierId(String haustierId) {
		this.haustierId = haustierId;
	}
}
