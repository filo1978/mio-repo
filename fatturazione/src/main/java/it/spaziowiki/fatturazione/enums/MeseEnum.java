package it.spaziowiki.fatturazione.enums;

public enum MeseEnum {

	GENNAIO("GENNAIO",1),
	FEBBRAIO("FEBBRAIO",2),
	MARZO("MARZO",3),
	APRILE("APRILE",4),
	MAGGIO("MAGGIO",5),
	GIUGNO("GIUGNO",6),
	LUGLIO("LUGLIO",7),
	AGOSTO("AGOSTO",8),
	SETTEMBRE("SETTEMBRE",9),
	OTTOBRE("OTTOBRE",10),
	NOVEMBRE("NOVEMBRE",11),
	DICEMBRE("DICEMBRE",12);
	
	private String descrizione;
	private int ordine;
	
	
	private MeseEnum(String descrizione, int ordine) {
		this.descrizione = descrizione;
		this.ordine = ordine;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public int getOrdine() {
		return ordine;
	}
	
	public static MeseEnum getMeseEnum(int ordine) {
		for(MeseEnum mese:MeseEnum.values()) {
			if(mese.getOrdine()==ordine)
				return mese;
		}
		return null;
	}
	
	public static MeseEnum getMeseEnum(String descrizione) {
		for(MeseEnum mese:MeseEnum.values()) {
			if(mese.getDescrizione().equals(descrizione))
				return mese;
		}
		return null;
	}
	
}
