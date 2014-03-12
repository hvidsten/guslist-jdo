package edu.gac.mcs270.hvidsten.guslistjdo.server;

import java.util.List;

import edu.gac.mcs270.hvidsten.guslistjdo.client.GetPostDataService;
import edu.gac.mcs270.hvidsten.guslistjdo.shared.PostData;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GetPostDataServiceImpl extends RemoteServiceServlet implements
		GetPostDataService {
	public List<PostData> getPostDataFromServer(String search) { 
		return GusListModel.getSearchData(search);
	}

	@Override
	public List<PostData> getPostDataFromServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostData> getSearchDataFromServer(String search) {
		// TODO Auto-generated method stub
		return null;
	}
}
