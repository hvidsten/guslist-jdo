/*
 * Model class for GusList app. 
 * Note: Model is on server side. Messages passed to Model
 *  from Controller must be done through RPC methods. 
 *  Model has static methods to simplify RPC calls 
 *    (see AdDataServiceImpl.java)
 */
package edu.gac.mcs270.hvidsten.guslistjdo.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import edu.gac.mcs270.hvidsten.guslistjdo.shared.PostData;

public class GusListModel {
	static final PersistenceManagerFactory pmf = PMF.get();

	public static List<PostData> getPostData() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(PostData.class);
		List<PostData> posts = (List<PostData>) query.execute();
		// Child classes are loaded "lazily" - not until they are accessed.
		// To make sure they are loaded before the PersistenceManager closes,
		// we reference them here so they are forced to load.  
		for(PostData post: posts){
			post.getSeller();
			post.getBuyer();
		}
		return new ArrayList<PostData>(posts);
	}

	public static void storePost(PostData post) {
		PersistenceManager pm = pmf.getPersistenceManager();
		// Transactions lock all records in a datastore and keep them locked 
		//  until they are ready to commit their changes. This eliminates
		//  possibility of conflict of access
		try {
			pm.currentTransaction().begin();
			pm.makePersistent(post);
			pm.currentTransaction().commit();
		}
		finally {
		    if (pm.currentTransaction().isActive())
		      pm.currentTransaction().rollback();
		    if (!pm.isClosed())
		      pm.close();
		   }
	}
	
	public static void deletePost(PostData post) {
		PersistenceManager pm = JDOHelper.getPersistenceManager(post);
		pm.deletePersistent(post);
	}
}
