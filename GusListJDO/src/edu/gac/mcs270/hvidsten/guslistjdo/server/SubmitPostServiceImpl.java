package edu.gac.mcs270.hvidsten.guslistjdo.server;

import java.util.List;

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
	public String handleDeleteRequest(long postID) {
		List<PostData> data = GusListModel.getPostData();
		for (int i = 0; i < data.size(); i ++) {
			if (data.get(i).getId() == postID) {
				GusListModel.deletePost(data.get(i));
				return "post deleted okay";
			}
		}
		return "post not deleted";
		
	}

}
