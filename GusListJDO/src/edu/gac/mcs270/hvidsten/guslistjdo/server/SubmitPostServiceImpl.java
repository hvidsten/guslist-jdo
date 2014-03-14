package edu.gac.mcs270.hvidsten.guslistjdo.server;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.gac.mcs270.hvidsten.guslistjdo.client.SubmitPostService;
import edu.gac.mcs270.hvidsten.guslistjdo.shared.PostData;

@SuppressWarnings("serial")
public class SubmitPostServiceImpl extends 
           RemoteServiceServlet implements SubmitPostService{

	@Override
	public String submitPostToServer(PostData post) {
		GusListModel.storePost(post);
		return "post submitted okay";
	}
	
	@Override
	public String deletePost(long postID) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		PostData post = pm.getObjectById(PostData.class, postID);
		pm.deletePersistent(post);
		return "post deleted";
	}

}
