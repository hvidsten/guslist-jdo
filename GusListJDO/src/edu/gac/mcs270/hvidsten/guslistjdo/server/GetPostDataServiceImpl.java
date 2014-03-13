package edu.gac.mcs270.hvidsten.guslistjdo.server;

import java.util.ArrayList;
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
	public List<PostData> getSearchDataFromServer(String title) { 
		List<PostData> data = GusListModel.getPostData();
		List<PostData>searchData = new ArrayList<PostData>();
		for (int i = 0; i < data.size(); i ++) {
			if (data.get(i).getTitle().matches(title)) {
				searchData.add(data.get(i));
			}
		}
		return searchData;
	}
	
}

