package edu.gac.mcs270.hvidsten.guslistjdo.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.gac.mcs270.hvidsten.guslistjdo.shared.PostData;

public interface SubmitPostServiceAsync {
	public void submitPostToServer(PostData post,
			AsyncCallback<String> asyncCallback);

	void changePostToServer(long postId, PostData newPost,
			AsyncCallback<String> callback);

	public void deletePostFromServer(PostData post, AsyncCallback<String> asyncCallback);
}
