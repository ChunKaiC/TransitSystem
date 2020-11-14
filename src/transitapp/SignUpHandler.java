package transitapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpHandler implements EventHandler<ActionEvent>{

	private Stage stage;
	private TransitGui obj;
	private TextField name;
	private TextField email;
	private HashMap<String, CardHolder> users;

	public SignUpHandler(Stage stage, TransitGui transitGui, TextField name, TextField email, HashMap<String, CardHolder> users) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.obj = transitGui;
		this.name = name;
		this.email = email;
		this.users = users;
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String nameTxt = name.getText();
		String emailTxt = email.getText();
		if (users.get(emailTxt) == null) {
			if ((nameTxt.length() > 0) && (emailTxt.length() > 0) && (emailTxt.contains("@"))) {
				CardHolder newUser = new CardHolder(nameTxt, emailTxt);
				// WRITE TO FILE
				try {
					Writer.writeCardHolder(newUser);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					obj.userUI(stage, newUser);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				name.setText("Enter Name Please");
				email.setText("Enter Email Please");
			}
		}
		else {
			if (!(emailTxt.contains("@"))) {
				email.setText("Email Does Not Contain '@' Symbol");
			}
			else {
				email.setText("This Email Is Already Registered");
			}
			
		}
	}

}
