package edu.gac.mcs270.hvidsten.guslistjdo.server;

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
	public String changePostToServer(long postId, PostData newPost) {
		GusListModel.changePostData(postId, newPost);
		return "post changed okay";
	}

	
	@Override
	public String deletePostFromServer(PostData post) {
		GusListModel.deletePostData(post);
		return "post deleted okay";
	}
}
