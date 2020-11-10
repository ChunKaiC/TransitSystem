package transitapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;

public class TransitHandler implements EventHandler<ActionEvent>{

	
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String source = ((Button) arg0.getSource()).getText();
		//System.out.println(source);
		if (source.equals("Login")) {
			
		}
	}

}
