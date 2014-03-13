package edu.gac.mcs270.hvidsten.guslistjdo.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.gac.mcs270.hvidsten.guslistjdo.shared.PostData;

public interface SubmitPostServiceAsync {
	public void submitPostToServer(PostData post,
			AsyncCallback<String> asyncCallback);

	public void handleDeleteRequest(long postID, AsyncCallback<String> callback);
}
