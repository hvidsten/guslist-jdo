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
import edu.gac.mcs270.hvidsten.guslistjdo.shared.Seller;

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

	public static List<PostData> getSearchedPostData(String searchString) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(PostData.class);
		List<PostData> posts = getPostData();
		List<PostData> searchedPosts = new ArrayList();
		for(PostData post : posts) {
			if(post.getTitle().equals(searchString)) {
				searchedPosts.add(post);
			}
		}
		return new ArrayList<PostData>(searchedPosts);
	}

	public static void changePostData(PostData oldPost, PostData newPost) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(PostData.class);
		List<PostData> posts = (List<PostData>) query.execute();
		for(PostData post : posts) {
			if(post.equals(oldPost)) {
				pm.deletePersistent(post);
			}
		}
		pm.makePersistent(newPost);
	}

	public static void deletePostData(PostData postToDelete){
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(PostData.class);
		List<PostData> posts = (List<PostData>) query.execute();
		for(PostData post : posts) {
			if(post.getPostId() == postToDelete.getPostId()) {
				pm.deletePersistent(post);
			}
		}
			
		}
	}


