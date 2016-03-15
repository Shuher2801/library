package it.by.library.entity.enums;

public enum ListRole {
	
//ADMIN, USER
	USER("USER"),
	ADMIN("ADMIN");
	
	String type;
	
	private ListRole(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}


	@Override
	public String toString(){
		return this.type;
	}

	public String getName(){
		return this.name();
	}


}
