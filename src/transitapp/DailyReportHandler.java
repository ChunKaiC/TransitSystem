package transitapp;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DailyReportHandler implements EventHandler<ActionEvent> {

	private TextField tf;
	private TransitGui transitGui;
	private Stage stage;

	public DailyReportHandler(TextField tf, TransitGui transitGui, Stage stage) {
		this.tf = tf;
		this.transitGui = transitGui;
		this.stage = stage;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(this.tf.getText().equals("")) {
			this.tf.setText("Please enter a valid Input");
		}
		else {
			String[] data = tf.getText().split("-");
			LocalDate ld = LocalDate.of(Integer.parseInt(data[0]) , Integer.parseInt(data[1]), Integer.parseInt(data[2]));
			try {
				this.transitGui.showDailyReport(this.stage, ld);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}