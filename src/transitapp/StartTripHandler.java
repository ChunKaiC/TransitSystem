package transitapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class StartTripHandler implements EventHandler<ActionEvent>{

	private ComboBox<Card> cardList;
	private ComboBox<Location> lList;

	public StartTripHandler(ComboBox<Card> cardList, ComboBox<Location> list) {
		// TODO Auto-generated constructor stub
		this.cardList = cardList;
		this.lList = list;
		//System.out.println(cardList.getValue());
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(this.cardList.getValue());
		System.out.println(this.lList.getValue());
	}

}
