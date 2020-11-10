package transitapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class SignUpHandler implements EventHandler<ActionEvent>{

	private Stage stage;
	private TransitGui obj;

	public SignUpHandler(Stage stage, TransitGui transitGui) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.obj = transitGui;
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
