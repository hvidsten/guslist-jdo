/*
 * View class for GusList app.
 * Handles all GUI components and event callback mechanisms 
 */

package edu.gac.mcs270.hvidsten.guslistjdo.client;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.MenuItemSeparator;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.gac.mcs270.hvidsten.guslistjdo.shared.PostData;
import edu.gac.mcs270.hvidsten.guslistjdo.shared.Seller;

public class GusListView {
	private GusList control;
	// Needs to be instantiated as final - just once
	final PopupPanel searchPopup = new PopupPanel(false);

	public GusListView(){}
	
	public void setController(GusList gusList) {
		control = gusList;
	}

	public GusList getController() {
		return control;
	}
	
	public void viewWelcomePage(){
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		makeMenuBar(rootPanel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		rootPanel.add(horizontalPanel, 10, 79);
		horizontalPanel.setSize("412px", "211px");
		
		makeSideBar(horizontalPanel);
	}

	public void viewPostData(List<PostData> posts) {
		if(posts==null) return;
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		makeMenuBar(rootPanel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		rootPanel.add(horizontalPanel, 10, 79);
		
		makeSideBar(horizontalPanel);
		
		VerticalPanel dataListPanel = new VerticalPanel();
		horizontalPanel.add(dataListPanel);
		
		FlowPanel flowPanel = new FlowPanel();
		dataListPanel.add(flowPanel);
		
		Label progTitlebar = new Label("GusList");
		progTitlebar.addStyleName("appTitleBar");
		progTitlebar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flowPanel.add(progTitlebar);
		
		makePostTable(posts, flowPanel);
	}
	
	public void viewPostAdForm() {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		makeMenuBar(rootPanel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		rootPanel.add(horizontalPanel, 10, 79);
		
		makeSideBar(horizontalPanel);
		
		VerticalPanel dataListPanel = new VerticalPanel();
		horizontalPanel.add(dataListPanel);
		
		FlowPanel flowPanel = new FlowPanel();
		dataListPanel.add(flowPanel);
		
		Label progTitlebar = new Label("GusList");
		progTitlebar.addStyleName("appTitleBar");
		progTitlebar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flowPanel.add(progTitlebar);
		
		createPostAdForm(flowPanel);
	}
	private void createPostAdForm(FlowPanel flowPanel) {
		
		// Name TextBox
		HorizontalPanel namePanel = new HorizontalPanel();
		Label nameLabel = new Label("Name (First Last)");
		nameLabel.addStyleName("postLabel");
		namePanel.add(nameLabel);
		flowPanel.add(namePanel);
		final TextBox nameTextBox = new TextBox();
		flowPanel.add(nameTextBox);
		
		// Title TextBox
		HorizontalPanel titlePanel = new HorizontalPanel();
		Label titleLabel = new Label("Title of Your Post (e.g. car, bike, etc)");
		titleLabel.addStyleName("postLabel");
		titlePanel.add(titleLabel);
		flowPanel.add(titlePanel);
		final TextBox titleTextBox = new TextBox();
		flowPanel.add(titleTextBox);
		
		// Description TextArea
		HorizontalPanel descriptionPanel = new HorizontalPanel();
		Label descriptionLabel = new Label("Item Description");
		descriptionLabel.addStyleName("postLabel");
		descriptionPanel.add(descriptionLabel);
		flowPanel.add(descriptionPanel);
		final TextArea descriptionTextArea = new TextArea();
		flowPanel.add(descriptionTextArea);
		
		// Price TextBox
		HorizontalPanel pricePanel = new HorizontalPanel();
		Label priceLabel = new Label("Price ($)");
		priceLabel.addStyleName("postLabel");
		pricePanel.add(priceLabel);
		flowPanel.add(pricePanel);
		final TextBox priceTextBox = new TextBox();
		flowPanel.add(priceTextBox);
		
		// Submit Button
		Button submitButton = new Button("Submit Ad");
		submitButton.setStyleName("sideBarButton");
		submitButton.setText("Submit Ad");
		
		// Submit Button Click Handler
		submitButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String name = nameTextBox.getText();
				String title = titleTextBox.getText();
				String descr = descriptionTextArea.getText();
				double price = Double.parseDouble(priceTextBox.getText());
				// Validate entries
				if(name.length()>0 && title.length()>0 && price >=0.0){
					PostData post = new PostData(title,descr,price,
							new Seller(name), null);
					control.handlePostSubmit(post);
				}
				else {
					// Should send error message to user
				}
			}
	      });
		flowPanel.add(submitButton);
	}
	
	private void makePostTable(List<PostData> posts, FlowPanel flowPanel) {
		for(PostData post: posts){
			flowPanel.add(makePostRow(post));
		}
	}

	private HorizontalPanel makePostRow(final PostData post) {
		HorizontalPanel row = new HorizontalPanel();
		Label titleLabel = new Label(post.getTitle());
		titleLabel.addStyleName("postLabel");
		Label descrLabel = new Label(post.getDescription());
		descrLabel.addStyleName("postLabel");
		Label priceLabel = new Label("$"+post.getPrice());
		priceLabel.addStyleName("postLabel");
		Button infoButton = new Button("More Info");
		infoButton.addStyleName("postInfoButton");
		infoButton.setText("More Info");
		infoButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				viewPostData(post);
			}
	      });
		row.add(titleLabel);
		row.add(descrLabel);
		row.add(priceLabel);
		row.add(infoButton);
		return row;
	}
	
	private void viewPostData(final PostData post) {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		makeMenuBar(rootPanel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		rootPanel.add(horizontalPanel, 10, 79);
		
		makeSideBar(horizontalPanel);
		
		VerticalPanel dataListPanel = new VerticalPanel();
		horizontalPanel.add(dataListPanel);
		
		FlowPanel flowPanel = new FlowPanel();
		dataListPanel.add(flowPanel);
		
		Label progTitlebar = new Label("GusList");
		progTitlebar.addStyleName("appTitleBar");
		progTitlebar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flowPanel.add(progTitlebar);
		
		// Title TextBox
		HorizontalPanel titlePanel = new HorizontalPanel();
		Label titleLabel = new Label(post.getTitle());
		titleLabel.addStyleName("postTitle");
		titlePanel.add(titleLabel);
		flowPanel.add(titlePanel);

		
		// Name TextBox
		HorizontalPanel namePanel = new HorizontalPanel();
		Label nameLabel = new Label("Sold by: " );//+ post.getSeller().getName());
		nameLabel.addStyleName("postBody");
		namePanel.add(nameLabel);
		flowPanel.add(namePanel);
				
		// Description TextArea
		HorizontalPanel descriptionPanel = new HorizontalPanel();
		Label descriptionLabel = new Label("About: " + post.getDescription());
		descriptionLabel.addStyleName("postBody");
		descriptionPanel.add(descriptionLabel);
		flowPanel.add(descriptionPanel);
				
		// Price TextBox
		HorizontalPanel pricePanel = new HorizontalPanel();
		Label priceLabel = new Label("Going For: $" + post.getPrice());
		priceLabel.addStyleName("postBody");
		pricePanel.add(priceLabel);
		flowPanel.add(pricePanel);
				
		// Edit Post Button
		Button editPostButton = new Button("Edit Ad");
		editPostButton.setStyleName("sideBarButton");
		editPostButton.setText("Edit Ad");
				
		// Edit Post Click Handler
		editPostButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				viewEditPostAdForm(post);
			}
		});
		
		// Delete Post Button
		Button deletePostButton = new Button("Delete Ad");
		deletePostButton.setStyleName("sideBarButton");
		deletePostButton.setText("Delete Ad");
						
		// Delete Post Click Handler
		deletePostButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				////////Andy this is where you need to start
			}
		});
		
		flowPanel.add(editPostButton);
		flowPanel.add(deletePostButton);
	}
	
	public void viewEditPostAdForm(PostData post) {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		makeMenuBar(rootPanel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		rootPanel.add(horizontalPanel, 10, 79);
		
		makeSideBar(horizontalPanel);
		
		VerticalPanel dataListPanel = new VerticalPanel();
		horizontalPanel.add(dataListPanel);
		
		FlowPanel flowPanel = new FlowPanel();
		dataListPanel.add(flowPanel);
		
		Label progTitlebar = new Label("GusList");
		progTitlebar.addStyleName("appTitleBar");
		progTitlebar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flowPanel.add(progTitlebar);
		
		createEditPostAdForm(post, flowPanel);
	}
	private void createEditPostAdForm(final PostData post, FlowPanel flowPanel) {
		
		// Name TextBox
		HorizontalPanel namePanel = new HorizontalPanel();
		Label nameLabel = new Label("Name (First Last)");
		nameLabel.addStyleName("postLabel");
		namePanel.add(nameLabel);
		flowPanel.add(namePanel);
		final TextBox nameTextBox = new TextBox();
		nameTextBox.setText("");     //post.getSeller().getName());
		flowPanel.add(nameTextBox);
		
		// Title TextBox
		HorizontalPanel titlePanel = new HorizontalPanel();
		Label titleLabel = new Label("Title of Your Post (e.g. car, bike, etc)");
		titleLabel.addStyleName("postLabel");
		titlePanel.add(titleLabel);
		flowPanel.add(titlePanel);
		final TextBox titleTextBox = new TextBox();
		titleTextBox.setText(post.getTitle());
		flowPanel.add(titleTextBox);
		
		// Description TextArea
		HorizontalPanel descriptionPanel = new HorizontalPanel();
		Label descriptionLabel = new Label("Item Description");
		descriptionLabel.addStyleName("postLabel");
		descriptionPanel.add(descriptionLabel);
		flowPanel.add(descriptionPanel);
		final TextArea descriptionTextArea = new TextArea();
		descriptionTextArea.setText(post.getDescription());
		flowPanel.add(descriptionTextArea);
		
		// Price TextBox
		HorizontalPanel pricePanel = new HorizontalPanel();
		Label priceLabel = new Label("Price ($)");
		priceLabel.addStyleName("postLabel");
		pricePanel.add(priceLabel);
		flowPanel.add(pricePanel);
		final TextBox priceTextBox = new TextBox();
		priceTextBox.setText("" + post.getPrice());
		flowPanel.add(priceTextBox);
		
		// Save Changes Button
		Button saveChangesButton = new Button("Save Changes to Ad");
		saveChangesButton.setStyleName("sideBarButton");
		saveChangesButton.setText("Save Changes to Ad");
		
		// Submit Button Click Handler
		saveChangesButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String name = nameTextBox.getText();
				String title = titleTextBox.getText();
				String descr = descriptionTextArea.getText();
				double price = Double.parseDouble(priceTextBox.getText());
				// Validate entries
				if(name.length()>0 && title.length()>0 && price >=0.0){
					PostData post = new PostData(title,descr,price,
							new Seller(name), null);
					control.handlePostSubmit(post);
				}
				else {
					PostData newPost = new PostData(title,descr,price,
							new Seller(name), null);
					control.handleChangePost(post, newPost);
				}
			}
	      });
		flowPanel.add(saveChangesButton);
	}

	public void makeMenuBar(RootPanel rp){
		MenuBar menuBar = new MenuBar(false);
		rp.add(menuBar, 94, 39);
		menuBar.setSize("326px", "32px");
		
		MenuItem menuHomeItem = new MenuItem("Home", false, new Command() {
			public void execute() {
				viewWelcomePage();
			}
		});
		menuHomeItem.setHTML("Home");
		menuBar.addItem(menuHomeItem);
		menuBar.addSeparator(new MenuItemSeparator());
		
		MenuItem menuSearchItem = new MenuItem("Search", false, new Command() {
			public void execute() {
				doPostSearch();
			}
		});
		menuSearchItem.setHTML("Search");
		menuBar.addItem(menuSearchItem);
		menuBar.addSeparator(new MenuItemSeparator());
		
		MenuItem menuSignInItem = new MenuItem("Sign In", false, (Command) null);
		menuSignInItem.setHTML("Sign In");
		menuBar.addItem(menuSignInItem);
		menuBar.addSeparator(new MenuItemSeparator());
		
		MenuItem menuContactItem = new MenuItem("Contact", false, (Command) null);
		menuContactItem.setHTML("Contact");
		menuBar.addItem(menuContactItem);
		menuBar.addSeparator(new MenuItemSeparator());
		
		MenuItem menuHelpItem = new MenuItem("Help", false, (Command) null);
		menuHelpItem.setHTML("Help");
		menuBar.addItem(menuHelpItem);
	}

	public void makeSideBar(HorizontalPanel hp){
		VerticalPanel sidePanel = new VerticalPanel();
		hp.add(sidePanel);
		sidePanel.setSize("72px", "98px");
		
		Button postAdButton = new Button("Post Ad");
		postAdButton.setStyleName("sideBarButton");
		postAdButton.setText("Post Ad");
		//add a clickListener to the button
		postAdButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				viewPostAdForm();
			}
	      });
		sidePanel.add(postAdButton);
		
		Button viewAdsButton = new Button("View Ads");
		viewAdsButton.setStyleName("sideBarButton");
		viewAdsButton.setText("View Ads");
		//add a clickListener to the button
		viewAdsButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				control.viewAdDataFromServer();
			}
	      });
		sidePanel.add(viewAdsButton);
		
		Hyperlink adminHyperlink = new Hyperlink("Admin Page", false, "newHistoryToken");
		sidePanel.add(adminHyperlink);
		
	}


	protected void doPostSearch() {		
		VerticalPanel content = new VerticalPanel();
		content.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		HorizontalPanel inputRow = new HorizontalPanel();
		Label searchTermLabel = new Label("Search Title Term: ");
		final TextBox searchTermTextBox = new TextBox();
		inputRow.add(searchTermLabel);
		inputRow.add(searchTermTextBox);
		
		HorizontalPanel btnRow = new HorizontalPanel();
		btnRow.setStyleName("search-button-row");
		Button cancelBtn = new Button("Cancel");
		cancelBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				searchPopup.hide();
			}
	      });
		Button searchBtn = new Button("Search");
		searchBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				control.handleTitleSearchRequest(searchTermTextBox.getText());
				searchPopup.hide();
			}
	      });
		btnRow.add(cancelBtn);
		btnRow.add(new Label(""));
		btnRow.add(searchBtn);
		
		content.add(inputRow);
		content.add(btnRow);
		searchPopup.setWidget(content);
		searchPopup.center();
	}
	
	public void sendSuccessfulPostmessage() {
		Window.alert("Post was successfully stored.");
	}
}
