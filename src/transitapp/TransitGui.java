package transitapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TransitGui extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
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
		login.setPrefWidth(100);
		login.setOnAction(new TransitHandler());
		Button signup = new Button("Sign Up");
		signup.setPrefWidth(100);
		signup.setOnAction(new TransitHandler());
		Button adminin = new Button("Admin Log In");
		adminin.setPrefWidth(100);
		adminin.setOnAction(new TransitHandler());
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
	
	public static void main(String[] args) {
		launch(args);
	}

}
