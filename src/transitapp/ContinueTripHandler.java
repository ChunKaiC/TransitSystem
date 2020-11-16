package transitapp;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ContinueTripHandler implements EventHandler<ActionEvent> {


	private Stage stage;
	private Card selectedCard;
	private Location currL;
	private CardHolder user;
	private ComboBox<Location> combo;
	private Label balance;
	private Label currLocation;
	private Label atInjuction;
	private Button tapOn;
	private Button tapOff;

	public ContinueTripHandler(Stage stage, Card selectedCard, Location currL, CardHolder user,
			ComboBox<Location> posibleDest, Label balance, Label currLocation, Label atInjuction, Button tapOn, Button tapOff) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.selectedCard = selectedCard;
		this.currL = currL;
		this.user = user;
		this.combo = posibleDest;
		this.balance = balance;
		this.currLocation = currLocation;
		this.atInjuction = atInjuction;
		this.tapOn = tapOn;
		this.tapOff = tapOff;
	}

	// Take in chosen destination from combobox when tapon clicked, set tapoff to not clickable if subway not tapped on. render gui with new currlocation and new balance
	// write to file card, 
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Button source = (Button) arg0.getSource();
		if (!(this.combo.getValue() == null)) {
			if (source.getText().equals("Tap On")) {
				this.tapOff.setDisable(false);
				this.tapOn.setDisable(true);
				this.combo.setDisable(true);
				Location nextDest = this.combo.getValue();
				this.currL = nextDest;
				this.updateAtInjuction();
				this.currLocation.setText("Current Location: " + this.currL);
				try {
					this.user.tapOn(currL, this.selectedCard.getCard_id(), LocalDateTime.now(), false);
					Writer.removeCard(selectedCard, user);
					Writer.writeCard(user.getEmail(), "" + selectedCard.getBalance(), "" + selectedCard.getCard_id(), true, selectedCard.getTimeInitialized());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.balance.setText("" + this.selectedCard.getBalance());
			}
			if (source.getText().equals("Tap Off")) {
				// write event to file and update current location and call getPosibleDests and add that return to combo box
				// re endable tap on and combo box with new possible destinations then write to file if bus, if station call tap off
				Location nextDest = this.combo.getValue();
				System.out.println(nextDest);
				this.tapOff.setDisable(true);
				this.tapOn.setDisable(false);
				this.combo.setDisable(false);
				this.currL = nextDest;
				this.combo.setItems(getPosibleDests());
				if (this.currL instanceof Stop) {
					//write to events
					try {
						Writer.writeEvent("tapOff", this.currL.getLocation(), this.selectedCard.getCard_id(), LocalDateTime.now(), this.user.getEmail());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					try {
						this.user.tapOff((Station)currL, selectedCard.getCard_id(), LocalDateTime.now(), false);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				
			}
			if (source.getText().equals("End Trip")) {
				if (this.tapOn.isDisabled()) {
					// write event to file
				}
			}
		}
	}
	
	public ObservableList<Location> getPosibleDests() {
		HashMap<String, Stop> stops = StartUp.stops;
		HashMap<String, Station> stations = StartUp.stations;
		ArrayList<TransitRoutes> busRoutes = StartUp.busRoutes;
		ArrayList<TransitRoutes> subwayRoutes = StartUp.subwayRoutes;
		ObservableList<Location> oList = FXCollections.observableArrayList();
		if (currL instanceof Stop) {
			for (TransitRoutes r : busRoutes) {
				boolean found = false;
				for (Location l : r.getRoute()) {
					if (found) {
						oList.add(l);
					}
					if (l.getLocation().equals(currL.getLocation())) {
						found = true;
					}
				}
			}
		}
		else {
			for (TransitRoutes r : subwayRoutes) {
				//boolean found = false;
				for (Location l : r.getRoute()) {
					if (!l.getLocation().equals(currL.getLocation())) {
						oList.add(l);
					}
				}
			}
		}
		ObservableList<Location> oList2 = FXCollections.observableArrayList();
		for (Location l : oList) {
			//Location stop = stops.get(l.getLocation());
			//Location station = stations.get(l.getLocation());
			if (l instanceof Stop && l.getAtInjuction()) {
				Station station = stations.get(l.getLocation());
				oList2.add(station);
			}
			if (l instanceof Station && l.getAtInjuction()) {
				Stop stop = stops.get(l.getLocation());
				oList2.add(stop);
			}
		}
		oList.addAll(oList2);
		return oList;
	}
	
	public void updateAtInjuction() {
		if (currL.getAtInjuction()) {
			if (currL instanceof Stop) {
				atInjuction.setText("This Stop Has a Station");
			}
			else {
				atInjuction.setText("This Station Has a Stop");
			}
		}
		else {
			if (currL instanceof Stop) {
				atInjuction.setText("This Stop Does Not Have a Station");
			}
			else {
				atInjuction.setText("This Station Does Not Have a Stop");
			}
		}
	}

}











