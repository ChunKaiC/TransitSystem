package transitapp;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserFunctionHandler implements EventHandler<ActionEvent>{

	private CardHolder user;
	private TransitGui obj;
	private Stage stage;
	private HashMap<String, Station> stations;
	private HashMap<String, Stop> stops;

	public UserFunctionHandler(CardHolder user, TransitGui transitGui, Stage stage, HashMap<String, Stop> stops, HashMap<String, Station> stations) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.obj = transitGui;
		this.stage = stage;
		this.stops = stops;
		this.stations = stations;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		String source = ((Button) event.getSource()).getText();
		System.out.println(source.equals("User Functions"));
		if (source.equals("User Functions")) {
			this.obj.userFunctionsUI(this.stage, this.user, this.stops, this.stations);
		}
		if (source.equals("Begin a Trip")) {
			this.obj.UserUIAfter(this.stage, this.user, this.stops, this.stations);
		}
	}

}
