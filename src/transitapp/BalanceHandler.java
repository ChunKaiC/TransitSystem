package transitapp;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class BalanceHandler implements EventHandler<ActionEvent> {

	private ComboBox<Card> value;
	private CardHolder user;

	public BalanceHandler(ComboBox<Card> balanceList, CardHolder user) {
		this.value = balanceList;
		this.user = user;
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("!");
		Card currCard = this.value.getValue();
		String source = ((Button) arg0.getSource()).getText();
		if (source.equals("Add $10 To Balance")) {
			currCard.addBalance(10, LocalDate.now());
			System.out.println("@");
			try {
				Writer.removeCard(currCard, user);
			} catch (IOException e) {
				//System.out.println("HELLOOO????");
				e.printStackTrace();
			}
			try {
				Writer.writeCard(user.getEmail(), "" + (currCard.getBalance()), "" + currCard.getCard_id(), currCard.isActivated(), currCard.getTimeInitialized());
				//StartUp.main();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (source.equals("Add $20 To Balance")) {
			currCard.addBalance(20, LocalDate.now());
			try {
				Writer.removeCard(currCard, user);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Writer.writeCard(user.getEmail(), "" + (currCard.getBalance()), "" + currCard.getCard_id(), currCard.isActivated(), currCard.getTimeInitialized());
				//StartUp.main();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (source.equals("Add $50 To Balance")) {
			currCard.addBalance(50, LocalDate.now());
			try {
				Writer.removeCard(currCard, user);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Writer.writeCard(user.getEmail(), "" + (currCard.getBalance()), "" + currCard.getCard_id(), currCard.isActivated(), currCard.getTimeInitialized());
				//StartUp.main();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		try {
			Writer.removeCard(currCard, user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			//System.out.println("Balance: " + currCard.getBalance());
			Writer.writeCard(user.getEmail(), "" + currCard.getBalance(), "" + currCard.getCard_id(), currCard.isActivated(), currCard.getTimeInitialized());
			StartUp.main();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
	}

}
