package transitapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;

public class LoginHandler implements EventHandler<ActionEvent>{

	private TextField name;
	private TextField email;
	private TextField adminId;
	
	public LoginHandler(TextField name, TextField email) {
		this.name = name;
		this.email = email;
	}
	
	public LoginHandler(TextField adminId) {
		this.adminId = adminId;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String source = ((Button) arg0.getSource()).getText();
		//System.out.println(source);
		if (source.equals("Login")) {
			
		}
	}

}
