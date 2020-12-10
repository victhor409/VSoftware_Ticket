package views;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import entities.Ticket;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class TicketListController implements Initializable{
	
	@FXML
	private TableView<Ticket> tableViewTicket;
	
	@FXML
	private TableColumn<Ticket, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Ticket, String> tableColumnNome;
	
	@FXML
	private TableColumn<Ticket, String> tableColumnClient;
	
	@FXML
	private TableColumn<Ticket, Long>  tableColumnCnpj;
	
	@FXML
	private TableColumn<Ticket, Date> tableColumnDate;
	
	@FXML
	private Button btNew;
	
	@FXML
	public void OnBtAction() {
		loadView("/views/NewTicket.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		}
		catch (IOException e) {
			e.getMessage();
		}
	}
	
	
}
