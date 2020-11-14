package transitapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserFunctionHandler implements EventHandler<ActionEvent>{

	private CardHolder user;
	private TransitGui obj;
	private Stage stage;
	private HashMap<String, Station> stations;
	private HashMap<String, Stop> stops;
	private ComboBox<Card> list;
	private TextField cNameTxt;
	private static Card value = null;
	
	public UserFunctionHandler(CardHolder user, TransitGui transitGui, Stage stage, HashMap<String, Stop> stops, HashMap<String, Station> stations) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.obj = transitGui;
		this.stage = stage;
		this.stops = stops;
		this.stations = stations;
	}

	public UserFunctionHandler(ComboBox<Card> cardListSus) {
		// TODO Auto-generated constructor stub
		this.list = cardListSus;
	}
	
	public UserFunctionHandler(TextField cNameTxt, CardHolder user, TransitGui transitGui, Stage stage, HashMap<String, Stop> stops, HashMap<String, Station> stations) {
		// TODO Auto-generated constructor stub
		this.cNameTxt = cNameTxt;
		this.user = user;
		this.obj = transitGui;
		this.stage = stage;
		this.stops = stops;
		this.stations = stations;
	}


	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() instanceof Button) {
			String source = ((Button) event.getSource()).getText();
			//System.out.println(source.equals("User Functions"));
			if (source.equals("User Functions")) {
				try {
					this.obj.userFunctionsUI(this.stage, this.user, this.stops, this.stations);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (source.equals("Begin a Trip")) {
				this.obj.UserUIAfter(this.stage, this.user, this.stops, this.stations);
			}
			if (source.equals("Activate Selected Card")) {
				if (!(UserFunctionHandler.value == null)) {
					//System.out.println(UserFunctionHandler.value.isActivated() + "hi");
					UserFunctionHandler.value.activate();
					
					// When Completed
					//Writer.removeCard(UserFunctionHandler.value, this.user);
					//Writer.writeCard(this.user.getEmail(), UserFunctionHandler.value.getBalance(), UserFunctionHandler.value.getCard_id());
					
					
					UserFunctionHandler.value = null;
					//System.out.println(UserFunctionHandler.value.isActivated());
					try {
						this.obj.userFunctionsUI(this.stage, this.user, this.stops, this.stations);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (source.equals("Change Name")) {
				if (!(this.cNameTxt.getText() == "")) {
					
					try {
						Writer.removeCardHolder(this.user);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					this.user.setName(this.cNameTxt.getText());
					
					try {
						Writer.writeCardHolder(this.user);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						this.obj.userFunctionsUI(this.stage, this.user, this.stops, this.stations);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (source.equals("Suspend Selected Card")) {
				if (!(UserFunctionHandler.value == null)) {
					UserFunctionHandler.value.desactivate();
					// When Completed
					//Writer.removeCard(UserFunctionHandler.value, this.user);
					//Writer.writeCard(this.user.getEmail(), UserFunctionHandler.value.getBalance(), UserFunctionHandler.value.getCard_id());
					UserFunctionHandler.value = null;
					//System.out.println(UserFunctionHandler.value.isActivated());
					try {
						this.obj.userFunctionsUI(this.stage, this.user, this.stops, this.stations);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
		}
		else {
			UserFunctionHandler.value = (Card) this.list.getValue();
			//System.out.println(UserFunctionHandler.value);
		}

	}

}
