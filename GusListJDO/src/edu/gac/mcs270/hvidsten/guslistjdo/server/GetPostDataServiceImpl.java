package edu.gac.mcs270.hvidsten.guslistjdo.server;

import java.util.List;

import edu.gac.mcs270.hvidsten.guslistjdo.client.GetPostDataService;
import edu.gac.mcs270.hvidsten.guslistjdo.shared.PostData;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GetPostDataServiceImpl extends RemoteServiceServlet implements
		GetPostDataService {
	public List<PostData> getPostDataFromServer() { 
		return GusListModel.getPostData();
	}

	@Override
	public List<PostData> getSearchDataFromServer(String title) {
		System.out.println("inside getsearchdataimpl");
		return GusListModel.getSearchData(title);
	
	}
}
