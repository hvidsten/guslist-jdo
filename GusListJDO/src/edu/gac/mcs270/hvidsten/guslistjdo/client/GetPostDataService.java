package edu.gac.mcs270.hvidsten.guslistjdo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.gac.mcs270.hvidsten.guslistjdo.shared.PostData;

/**
 * The client side stub for the RPC service.
 */
// Note: Name needs to match url-pattern in web.xml
@RemoteServiceRelativePath("postdata") 
public interface GetPostDataService extends RemoteService {
	public List<PostData> getPostDataFromServer();
	public List<PostData> getSearchDataFromServer(String title);
}
