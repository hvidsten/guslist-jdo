package edu.gac.mcs270.hvidsten.guslistjdo.shared;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class PostData implements Serializable {
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	// Need to define the key this way so that a Seller can be passed
	// as data through RPC services.   
	// See https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata#Keys
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	@Persistent
	private String title="no title";
	@Persistent
	private String description="empty";
	@Persistent
	private double price=0.0;
	
	// Need to define the Seller and Buyer as "Unowned" child objects, 
	//  as they do not disappear when PostData object is deleted. 
	@Persistent
	@Unowned
	private Seller seller=null;
	@Persistent
	@Unowned
	private Buyer buyer=null;
	
	// GWT serializable Objects need a no=argument constructor
	public PostData() {}
	
	public PostData(String t, String d, double p, Seller s, Buyer b){
		title = t;
		description = d;
		price = p;
		seller = s;
		buyer = b;
		
		
	}

	public String getTitle() {
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
	
	public long getPostID() {
		return id;
	}
	
	public String toString() {
		return "Post title = "+ title +
				"\n description = "+description +
				"\n price = "+ price;
	}
}

