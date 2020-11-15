package transitapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BackButtonsHandler implements EventHandler<ActionEvent> {

	private TransitGui transitGui;
	private String mode;
	private Stage stage;

	public BackButtonsHandler(TransitGui transitGui, Stage stage, String mode) {
		this.transitGui = transitGui;
		this.mode = mode;
		this.stage = stage;
	}

	@Override
	public void handle(ActionEvent arg0) {
		if(this.mode.equals("Admin")) {
			this.transitGui.adminUI(stage, new Label(""));
		}
		else if(this.mode.equals("HomePage")) {
			try {
				this.transitGui.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
