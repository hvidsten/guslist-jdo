package edu.gac.mcs270.hvidsten.guslistjdo.shared;

import java.io.Serializable;

public class Seller  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name="";
	
	public Seller() {}
	
	public Seller(String string) {
		name = string;
	}
	
	public String getName() {
		return name;
	}
}
