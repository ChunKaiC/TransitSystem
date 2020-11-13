package transitapp;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TransitGui extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		// DATA LOADING
		//StartUp.loadStops();
		//StartUp.loadStation();
		//StartUp.l
		HashMap<String, CardHolder> users = StartUp.loadCardHolders();
		
		//
		stage.setTitle("UTM Metro");
		//stage.setWidth(900);
		//stage.setHeight(700);
		stage.setMaxHeight(700);
		stage.setMinHeight(700);
		stage.setMaxWidth(900);
		stage.setMinWidth(900);
		//StackPane pane = new StackPane();
		
		// nodes
		// Welcome label
		Label welcome = new Label("Welcome to UTM Metro");
		welcome.setMinSize(150, 100);
		welcome.setFont(new Font(50));
		welcome.setTextFill(Color.web("#fbfbfb"));
		// 
		// Log in area
		TextField name = new TextField();
		//name.setMaxSize(450, 500);
		name.setPrefSize(400, 30);
		TextField email = new TextField();
		email.setPrefSize(400, 30);
		TextField admin = new TextField();
		email.setPrefSize(400, 30);
		//email.setMaxSize(450, 500);
		Label ntxt = new Label("Name: ");
		ntxt.setFont(new Font(14));
		ntxt.setTextFill(Color.web("#fbfbfb"));
		//ntxt.setAlignment(Pos.CENTER);
		Label etxt = new Label("Email: ");
		etxt.setFont(new Font(14));
		etxt.setTextFill(Color.web("#fbfbfb"));
		Label atxt = new Label("Admin ID");
		atxt.setFont(new Font(14));
		atxt.setTextFill(Color.web("#fbfbfb"));
		//
		//StackPane pane = new StackPane(welcome);
		GridPane center = new GridPane();
		center.setMinSize(500, 500);
		center.setMaxSize(500, 500);
		center.setVgap(5);
		center.setHgap(5);
		
		//center.setPrefSize(900,700);
		// btns for log in or sign up
		Button login = new Button("Log In");
		//login.setTextFill(Color.web("#a1ad8a"));
		//login.setStyle("fx-background-color: black;");
		//login.setStyle("fx-background-radius: 0;");
		//login.setStyle("fx-border-color: black;");
		login.setPrefWidth(100);
		//login.setBlendMode(Color.web("#a1ad8a"));
		login.setOnAction(new LoginHandler(name, email, stage, this, users));
		Button signup = new Button("Sign Up");
		signup.setPrefWidth(100);
		signup.setOnAction(new SignUpHandler(stage, this, name, email, users));
		//signup.setOnAction(new LoginHandler(name, email));
		Button adminin = new Button("Admin Log In");
		adminin.setPrefWidth(100);
		adminin.setOnAction(new LoginHandler(admin, stage, this));
		//
		//User
		center.add(ntxt, 0, 0);
		center.add(name, 1, 0);
		center.add(etxt, 0, 2);
		center.add(email, 1, 2);
		center.add(login, 1, 3);
		center.add(signup, 0, 3);
		//Admin
		center.add(atxt, 0, 6);
		center.add(admin, 1, 6);
		center.add(adminin, 0, 7);
		center.setAlignment(Pos.CENTER);
		//center.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");
		
		//Image back = new Image("C:\\Users\\faisa\\a2-a2-the-danny-treatment\\src\backdrop.png", 900, 700, true, true);
		Image back = new Image("file:resources/backdrop.png");
		ImageView back2 = new ImageView();
		back2.setImage(back);
		//back2.setCache(true);
		StackPane pane = new StackPane(back2, welcome, center);
		//StackPane pane = new StackPane( back2);
		StackPane.setAlignment(welcome, Pos.TOP_CENTER);
		//StackPane.setAlignment(center, Pos.CENTER);
		center.setAlignment(Pos.CENTER);
		//name.setMaxSize(250, 50);
		//StackPane.setAlignment(email, Pos.CENTER);
		//email.setMaxSize(250, 50);
		
		// mandatory code
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
	}
	
	public void userUI(Stage stage, CardHolder user) throws FileNotFoundException {
		StackPane pane = new StackPane();
		Image back = new Image("file:resources/backdrop.png");
		ImageView back2 = new ImageView();
		back2.setImage(back);
		pane.getChildren().add(back2);
		
		
		// loading
		
		HashMap<String, Stop> stops = StartUp.loadStops();
		HashMap<String, Station> stations = StartUp.loadStation();
		StartUp.loadBusRoutes();
		StartUp.loadSubwayRoute();
		
		
		
		// combo box
		GridPane center = new GridPane();
		
		ObservableList<Location> oList = FXCollections.observableArrayList();
		for (Stop s : stops.values()) { 
			oList.add(s);
		}
		for (Station s : stations.values()) { 
			oList.add(s);
		}
		ComboBox list = new ComboBox(oList);
		list.setPrefSize(400, 30);
		center.add(list, 1, 1);
		Label loc = new Label("Choose Start Location:");
		loc.setTextFill(Color.web("#fbfbfb"));
		center.add(loc, 0, 1);
		//list.setPrefSize(prefWidth, prefHeight);
		ObservableList<Card> cList = FXCollections.observableArrayList();
		for (Card s : user.getCards()) {
			if (s.hasBalance()) {
				cList.add(s);
			}
		}
		ComboBox cardList = new ComboBox(cList);
		cardList.setPrefSize(400, 30);
		Label c = new Label("Choose A Card:");
		c.setTextFill(Color.web("#fbfbfb"));
		center.add(c, 0, 0);
		center.add(cardList, 1, 0);
		center.setAlignment(Pos.CENTER);
		center.setHgap(10);
		center.setVgap(10);
		
		//
		
		
		// User functions button that takes to another screen that : Edit name, suspend card, view recent trips, create card. then returns to this screen when done
		Button startTrip = new Button("Start Trip");
		Button userFunctions = new Button("User Functions");
		center.add(startTrip, 0, 3);
		center.add(userFunctions, 1, 3);
		//startTrip.setOnAction();
		userFunctions.setOnAction(new UserFunctionHandler(user, this, stage, stops, stations));
		
		
		// User Info
		VBox userInfo = new VBox();
		Label name = new Label("Name: " + user.getName());
		name.setTextFill(Color.web("#fbfbfb"));
		name.setFont(new Font(25));
		Label email = new Label("Email: " +user.getEmail());
		email.setTextFill(Color.web("#fbfbfb"));
		email.setFont(new Font(25));
		//Label cards = new Label("Cards: " +user.getCards().toString());
		//cards.setTextFill(Color.web("#fbfbfb"));
		//Label recentTrips = new Label("RecentTrips: " +user.getRecentTrips().toString());
		//recentTrips.setTextFill(Color.web("#fbfbfb"));
		userInfo.getChildren().addAll(name, email);
		pane.getChildren().add(userInfo);
		userInfo.setAlignment(Pos.TOP_CENTER);
		
		//Label l1 = new Label("hello");
		//pane.getChildren().add(l1);
		// add list above everything else
		//pane.getChildren().add(list);
		pane.getChildren().add(center);
		
		Scene scene = new Scene(pane);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public void userFunctionsUI(Stage stage, CardHolder user, HashMap<String, Stop> stops,
			HashMap<String, Station> stations) {
		// show monthly cost
		// show recent trips
		// suspend or activate cards
		// change name
		System.out.println("hello");
		StackPane pane = new StackPane();
		Image back = new Image("file:resources/backdrop.png");
		ImageView back2 = new ImageView();
		back2.setImage(back);
		pane.getChildren().add(back2);
		
		// BUTTONS
		Button begin = new Button("Begin a Trip");
		//begin.setPrefSize(prefWidth, prefHeight);
		begin.setPrefWidth(150);
		Button suspend = new Button("Suspend Selected Card");
		suspend.setPrefWidth(150);
		Button cname = new Button("Change Name");
		cname.setPrefWidth(150);
		Button activate = new Button("Activate Selected Card");
		activate.setPrefWidth(150);
		
		// Text Field
		TextField cNameTxt = new TextField();
		
		// Show user data
		CardHolder currUser = user;
		Label name = new Label("Name: " + currUser.getName());
		name.setTextFill(Color.web("#fbfbfb"));
		name.setFont(new Font(15));
		Label email = new Label("Email: " + currUser.getEmail());
		email.setTextFill(Color.web("#fbfbfb"));
		email.setFont(new Font(15));
		//Label mCost = new Label("" + currUser.averageMonthlyCost());
		Label mCost = new Label("Averge Monthly Cost: " + "Axel fix your shit broooo");
		mCost.setTextFill(Color.web("#fbfbfb"));
		mCost.setFont(new Font(15));
		
		VBox top = new VBox();
		top.getChildren().add(name);
		top.getChildren().add(email);
		top.getChildren().add(mCost);
		pane.getChildren().add(top);
		top.setAlignment(Pos.TOP_CENTER);
		
		
		
		
		
		ObservableList<Card> cList = FXCollections.observableArrayList();
		for (Card s : user.getCards()) {
			if (s.isActivated()) {
				System.out.println(s.isActivated());
				cList.add(s);
			}
		}
		ComboBox cardListActive = new ComboBox(cList);
		
		ObservableList<Card> cList2 = FXCollections.observableArrayList();
		for (Card s : user.getCards()) {
			if (!s.isActivated()) {
				cList2.add(s);
			}
		}
		ComboBox cardListSus = new ComboBox(cList2);
		
		ArrayList<Trip> recent = currUser.getRecentTrips();		
		
		// grid pane
		GridPane center = new GridPane();
		center.setHgap(5);
		center.setVgap(5);
		center.add(cname, 0, 0);
		center.add(cNameTxt, 1, 0);
		center.add(suspend, 0, 1);
		center.add(cardListActive, 1, 1);
		center.add(activate, 0, 2);
		center.add(cardListSus, 1, 2);
		center.setAlignment(Pos.CENTER);
		pane.getChildren().add(center);		
		
		Scene scene =  new Scene(pane);
		stage.setScene(scene);
		stage.show();
	}
	
	public void adminUI(Stage stage) {
		
	}
	
	public void UserUIAfter(Stage stage, CardHolder user, HashMap<String, Stop> stops,
			HashMap<String, Station> stations) {
		// TODO Auto-generated method stub
		// do the same thing as userUI but dont load, just pass in stops and stations
	}

	
	public static void main(String[] args) {
		launch(args);
	}

	

	

	
}
