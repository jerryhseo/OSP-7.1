package com.kisti.osp.util.constants;

public enum OSPRepositoryTypes {
	USER_HOME("repository"),
	USER_JOBS("jobs"),
	PROVENANCE("provenance"),
	ICECAP("icecap"),
	SCIENCEAPP("scienceapp"),
	SPYGLASS("spyglass");
	
	@Override
	public String toString(){
		return this.value;
	}
	
	private OSPRepositoryTypes(String value) {
		this.value = value;
	}
	
	public boolean equal( String type ){
		return this.value.equalsIgnoreCase(type);
	}
	
	public String value(){
		return this.value;
	}
	
	private String value;
}
