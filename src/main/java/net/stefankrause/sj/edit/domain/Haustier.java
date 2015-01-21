package net.stefankrause.sj.edit.domain;

public class Haustier {
	private String id;
	private String name;
	private int kosten;
	
	public Haustier() {
	}
	
	public Haustier(String id, String name, int kosten) {
		super();
		this.id = id;
		this.name = name;
		this.kosten = kosten;
	}

	public int getKosten() {
		return kosten;
	}
	public void setKosten(int kosten) {
		this.kosten = kosten;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
