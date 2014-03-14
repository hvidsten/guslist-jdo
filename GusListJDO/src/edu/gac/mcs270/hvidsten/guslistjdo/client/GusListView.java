/*
 * View class for GusList app.
 * Handles all GUI components and event callback mechanisms 
 */

package edu.gac.mcs270.hvidsten.guslistjdo.client;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

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

import edu.gac.mcs270.hvidsten.guslistjdo.server.PMF;
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
		
		VerticalPanel postFormPanel = new VerticalPanel();
		horizontalPanel.add(postFormPanel);
		
		// Name input
		HorizontalPanel nameRow = new HorizontalPanel();
		Label nameLabel = new Label("Name (First Last");
		final TextBox nameTextbox = new TextBox();
		nameRow.add(nameLabel);
		nameRow.add(nameTextbox);
		postFormPanel.add(nameRow);
		
		// Title input
		HorizontalPanel titleRow = new HorizontalPanel();
		Label titleLabel = new Label("Title (e.g. car, bike, etc)");
		final TextBox titleTextbox = new TextBox();
		titleRow.add(titleLabel);
		titleRow.add(titleTextbox);
		postFormPanel.add(titleRow);
		
		// Description input
		HorizontalPanel descrRow = new HorizontalPanel();
		Label descrLabel = new Label("Item Short description");
		final TextArea descrText = new TextArea();
		descrText.setCharacterWidth(40);
		descrText.setVisibleLines(10);
		descrRow.add(descrLabel);
		descrRow.add(descrText);
		postFormPanel.add(descrRow);
		
		// Price input
		HorizontalPanel priceRow = new HorizontalPanel();
		Label priceLabel = new Label("Price ($)");
		final TextBox priceTextbox = new TextBox();
		priceTextbox.setVisibleLength(6);
		priceRow.add(priceLabel);
		priceRow.add(priceTextbox);
		postFormPanel.add(priceRow);
		
		// Submit button
		Button submitButton = new Button("Submit Ad");
		submitButton.setStyleName("sideBarButton");
		submitButton.setText("Submit Ad");
		
		submitButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String name = nameTextbox.getText();
				String title = titleTextbox.getText();
				String descr = descrText.getText();
				double price = Double.valueOf(priceTextbox.getText());
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
		postFormPanel.add(submitButton);
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
		//add a clickListener to the button
		infoButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// To Do
			}
	      });
		Button delete = new Button("Delete");
		delete.addStyleName("Delete");
		delete.setText("Delete");
		
		//add a clickListener to the button
		delete.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				control.handleDeleteRequest(post);
			}
		});
		row.add(titleLabel);
		row.add(descrLabel);
		row.add(priceLabel);
		row.add(infoButton);
		row.add(delete);
		return row;
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
	
	public void sendSuccessfulDeleteMessage() {
		Window.alert("Post was successfully deleted.");
	}
}
