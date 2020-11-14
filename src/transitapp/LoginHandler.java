package transitapp;

import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginHandler implements EventHandler<ActionEvent>{

	private TextField name;
	private TextField email;
	private TextField adminId;
	private Stage stage;
	private TransitGui obj;
	private HashMap<String, CardHolder> users;
	
	public LoginHandler(TextField name, TextField email, Stage stage, TransitGui obj, HashMap<String, CardHolder> users) {
		this.name = name;
		this.email = email;
		this.stage = stage;
		this.obj = obj;
		this.users = users;
	}
	
	public LoginHandler(TextField adminId, Stage stage, TransitGui obj) {
		this.adminId = adminId;
		this.stage = stage;
		this.obj = obj;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String source = ((Button) arg0.getSource()).getText();
		//System.out.println("Login");
		//System.out.println(source);
		//System.out.println(source);
		if (source.equals("Log In")) {
			//System.out.println("Login");
			String name = this.name.getText();
			String email = this.email.getText();
			System.out.println("lol");
			System.out.print(users);
			CardHolder user = users.get(email);
			if (!(user == null)) {
				String userName = user.getName();
				if (userName.equals(name)) {
					// user found
					// System.out.print("User Found");
					try {
						this.obj.userUI(this.stage, user);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					this.name.setText("Name and Email Do Not Match Directory of Users");
				}
			} else {
				this.email.setText("User Not Found!");
			}
		}
		if (source.equals("Admin Log In")) {
			if (adminId.getText().equals("Please Give Us 100% TA"))
			this.obj.adminUI(this.stage);
		}
	}

}
