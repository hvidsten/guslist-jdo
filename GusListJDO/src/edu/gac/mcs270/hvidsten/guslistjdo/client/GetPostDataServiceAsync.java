package edu.gac.mcs270.hvidsten.guslistjdo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.gac.mcs270.hvidsten.guslistjdo.shared.PostData;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GetPostDataServiceAsync {
	public void getPostDataFromServer(AsyncCallback<List<PostData>> asyncCallback);
	public void getSearchDataFromServer(String SearchTerm, AsyncCallback<List<PostData>> asyncCallback);
}

