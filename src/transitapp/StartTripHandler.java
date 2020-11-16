package transitapp;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class StartTripHandler implements EventHandler<ActionEvent>{

	private ComboBox<Card> cardList;
	private ComboBox<Location> lList;
	private TransitGui obj;
	private CardHolder user;
	private Stage stage;

	public StartTripHandler(Stage stage, CardHolder user, ComboBox<Card> cardList, ComboBox<Location> list, TransitGui transitGui) {
		// TODO Auto-generated constructor stub
		this.cardList = cardList;
		this.lList = list;
		this.obj = transitGui;
		this.user = user;
		this.stage = stage;
		//System.out.println(cardList.getValue());
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(((Button) arg0.getSource()).getText());
		//System.out.println(this.lList.getValue());
		if (((Button) arg0.getSource()).getText().equals("Start Trip") &&
				this.cardList.getValue() instanceof Card && this.lList.getValue() instanceof Location) {
			// pass in location chosen and card chosen 
			
			Card selectedCard = this.cardList.getValue();
			Location start = this.lList.getValue();
			try {
				this.obj.continueTrip(this.stage, selectedCard, start, this.user);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
