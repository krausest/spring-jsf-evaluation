package net.stefankrause.sj.edit.domain;


public enum Schuhinfo implements HasLabel {
	Unbekannt("Unbekannt"),
	Bekannt("Bekannt"),
	Keine_Fuesse("Keine Füße");
	
	private String label;

	private Schuhinfo(String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public int getId() {
		return super.ordinal();
	}
}