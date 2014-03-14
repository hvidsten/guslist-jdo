package edu.gac.mcs270.hvidsten.guslistjdo.client;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.gac.mcs270.hvidsten.guslistjdo.shared.PostData;


@RemoteServiceRelativePath("submitpost") 
public interface SubmitPostService extends RemoteService {
	public String submitPostToServer(PostData post);
	public String deletePostFromServer(PostData post);
}
