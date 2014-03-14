package edu.gac.mcs270.hvidsten.guslistjdo.shared;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class PostData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5539539987489885476L;

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	public String title="no title";
	@Persistent
	private String description="empty";
	@Persistent
	private double price=0.0;
	@Persistent(serialized ="true")
	private Seller seller=null;
	@Persistent(serialized ="true")
	private Buyer buyer=null;
	
	// GWT serializable Objects need a no=argument constructor
	public PostData() {}
	
	public PostData(String t, String d, double p, Seller s, Buyer b) {
		title = t;
		description = d;
		price = p;
		seller = s;
		buyer = b;
	}

	public long getId() {
		return id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getPrice(){
		return price;
	}
	
	public Seller getSeller(){
		return seller;
	}
	
	public Buyer getBuyer() {
		return buyer;
	}
	
	public String toString() {
		return "Post title = "+ title +
				"\n description = "+description +
				"\n price = "+ price;
	}
}

