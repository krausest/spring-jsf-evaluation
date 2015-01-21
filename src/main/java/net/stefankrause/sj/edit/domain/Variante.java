package net.stefankrause.sj.edit.domain;

import java.util.List;

public class Variante {
	private Schuhinfo schuhinfo;
	private int schuhanzahl;
	private int schuhpreis;
	private List<HaustierInfo> haustiere;
	
	public List<HaustierInfo> getHaustiere() {
		return haustiere;
	}
	public void setHaustiere(List<HaustierInfo> haustiere) {
		this.haustiere = haustiere;
	}
	public int getSchuhanzahl() {
		return schuhanzahl;
	}
	public void setSchuhanzahl(int schuhanzahl) {
		this.schuhanzahl = schuhanzahl;
	}
	public int getSchuhpreis() {
		return schuhpreis;
	}
	public void setSchuhpreis(int schuhpreis) {
		this.schuhpreis = schuhpreis;
	}
	public Schuhinfo getSchuhinfo() {
		return schuhinfo;
	}
	public void setSchuhinfo(Schuhinfo schuhinfo) {
		this.schuhinfo = schuhinfo;
	}
}
