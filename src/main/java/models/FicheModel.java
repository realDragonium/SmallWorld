package models;

public class FicheModel {
	
	private String name;
	private int defenseValue;
	private int attackValue;
	public enum ficheType {RAS, SPECIAL, SPECIALPOWER};
	private ficheType type;
	
	public int whatsMyAttackValue() {
		return attackValue;
	}
	
	public int whatsMyDeffenceValue() {
		return defenseValue;
	}
	
	public ficheType whatsMyType() {
		return type;
	}

}
