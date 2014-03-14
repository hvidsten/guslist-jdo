package edu.gac.mcs270.hvidsten.guslistjdo.client;

import java.util.List;

import javax.jdo.PersistenceManager;

import edu.gac.mcs270.hvidsten.guslistjdo.server.PMF;
import edu.gac.mcs270.hvidsten.guslistjdo.shared.PostData;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class GusList implements EntryPoint {
	private final GusListView glView = new GusListView();
	private final GetPostDataServiceAsync postDataService = GWT
			.create(GetPostDataService.class);
	private final SubmitPostServiceAsync submitPostService = GWT
			.create(SubmitPostService.class);

	public void onModuleLoad() {
	    // Wire controller to view
		//  Note: Model is on server side - can only
		//   communicate to Model through RPC calls
		//   Cannot wire it directly as a class attribute
		glView.setController(GusList.this);
		// Show welcome page
		glView.viewWelcomePage();
		//RootPanel rootPanel = RootPanel.get();
		//rootPanel.clear();
	}
	
	
	public GusListView getView() {
		return glView;
	}
	
	public void viewAdDataFromServer(){
		postDataService.getPostDataFromServer(
				new AsyncCallback<List<PostData>>() {
					public void onFailure(Throwable caught) {
						return;
					}

					@Override
					public void onSuccess(List<PostData> data) {
						glView.viewPostData(data);
					}
				});
	}

	public void handlePostSubmit(PostData post) {
		submitPostService.submitPostToServer(post, 
				new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				return;
			}

			@Override
			public void onSuccess(String result) {
				glView.sendSuccessfulPostmessage();
			}
		});
		
	}
	
	public void handleDeleteRequest(long postID) {
		submitPostService.deletePost(postID, new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				return;
			}
			public void onSuccess(String result) {
				glView.sendSuccessfulDeleteMessage();
			}
		});
	}

	public void handleTitleSearchRequest(String title) {

		// Need to implement servlet communication
		postDataService.getSearchDataFromServer(title, 
				new AsyncCallback<List<PostData>>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						System.out.println("fail");
						return;
					}

					@Override
					public void onSuccess(List<PostData> data) {
						// TODO Auto-generated method stub
						System.out.println("Success");
						glView.viewPostData(data);
					}
				
		});
	}
}
