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
	public String changePostToServer(PostData oldPost, PostData newPost) {
		GusListModel.changePostData(oldPost, newPost);
		return "post changed okay";
	}

}
