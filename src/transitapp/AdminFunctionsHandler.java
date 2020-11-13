package transitapp;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class AdminFunctionsHandler implements EventHandler<ActionEvent>{
	
	
	ComboBox value = null;
	private TransitGui obj;
	private Stage stage;
	private Button button;
	static String action = null;
	
	
	public AdminFunctionsHandler(ComboBox actionList) {
		this.value = actionList;
	}

	public AdminFunctionsHandler(TransitGui obj, Stage stage) {
		// TODO Auto-generated constructor stub
		this.obj = obj;
		this.stage = stage;
	}

	@Override
	public void handle(ActionEvent arg0) {
		if(arg0.getSource() instanceof ComboBox) {
			AdminFunctionsHandler.action = (String) this.value.getValue();
		}
		if(arg0.getSource() instanceof Button) {
//			String source = this.button.getText();
			
			if(AdminFunctionsHandler.action == "Get Daily Report") {
				this.obj.showDR(this.stage);
			}
			
			if(AdminFunctionsHandler.action == "Set Fair For Bus Routes") {
				this.obj.showSetFair(this.stage);
			}
			
			if(AdminFunctionsHandler.action == "Set Fair For Stations") {
				this.obj.showSetStation(this.stage);
			}
			
		}
	}

}
