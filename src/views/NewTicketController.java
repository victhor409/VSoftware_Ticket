package views;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewTicketController implements Initializable {
	
	@FXML
	private TextField txId;
	
	@FXML
	private TextField txName;
	
	@FXML
	private TextField txClient;
	
	@FXML
	private TextField txCnpj;
	
	@FXML
	private TextField txDate;
	
	@FXML
	private TextArea txDescription;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancel;
	
	@FXML
	public void onBtSaveAction() {
		System.out.println("Saved");
	}
	
	@FXML
	public void onBtCancelAction() {
		System.out.println("Canceled");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
