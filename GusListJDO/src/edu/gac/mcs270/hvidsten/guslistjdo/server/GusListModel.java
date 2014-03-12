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
		return new ArrayList<PostData>(posts);
	}

	public static void storePost(PostData post) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.makePersistent(post);
	}
	
	public static List<PostData> getSearchData(String searchKeyword) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(PostData.class);
		query.setFilter("this.title == searchKeyword");
		query.declareParameters("String searchKeyword");
		List<PostData> results = (List<PostData>) query.execute(searchKeyword);
		return new ArrayList<PostData>(results);
	}
	
	public static void deletePost(PostData post) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(PostData.class);
		List<PostData> posts = (List<PostData>) query.execute();
		for(PostData r:posts){
			if(r.getPostID() == post.getPostID()){
				pm.deletePersistent(r);
			}
		}
	}
}
