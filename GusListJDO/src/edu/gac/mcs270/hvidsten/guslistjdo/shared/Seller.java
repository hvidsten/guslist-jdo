package edu.gac.mcs270.hvidsten.guslistjdo.shared;

import java.io.Serializable;

import javax.jdo.annotations.Persistent;

public class Seller  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Persistent
	private String name="";
	
	public Seller() {}
	
	public Seller(String string) {
		name = string;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
