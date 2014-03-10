package edu.gac.mcs270.hvidsten.guslistjdo.shared;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;



@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class PostData implements Serializable {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String title="no title";
	@Persistent
	private String description="empty";
	@Persistent
	private double price=0.0;
	@Persistent(serialized ="true")
	private String seller="no seller";
	@Persistent(serialized ="true")
	private Buyer buyer=null;
	
	// GWT serializable Objects need a no=argument constructor
	public PostData() {}
	
	public PostData(String t, String d, double p, String s, Buyer b){
		title = t;
		description = d;
		price = p;
		seller = s;
		//setSeller(new Seller(s.getName()));
	    // give the person a new ContactProfiles reference so the update is detected
	 //   p.setContactProfiles(new ContactProfiles(p.getContactProfiles().get()));
		buyer = b;
	}
	
	public PostData(String t, String d, double p, String s){
		title = t;
		description = d;
		price = p;
		seller = s;
		//setSeller(new Seller(s.getName()));
	}

	public long getPostId(){
		return this.id;
	}
	
	public void setPostId(long id) {
		this.id = id;
	}
	
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getSeller(){
		return seller;
	}
	
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	public Buyer getBuyer() {
		return buyer;
	}
	
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
	public String toString() {
		return "Post title = "+ title +
				"\n description = "+description +
				"\n price = "+ price;
	}
}

