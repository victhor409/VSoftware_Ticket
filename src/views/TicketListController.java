package views;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entities.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.service.TicketService;

public class TicketListController implements Initializable{
	
	private TicketService service;
	
	@FXML
	private TableView<Ticket> tableViewTicket;
	
	@FXML
	private TableColumn<Ticket, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Ticket, String> tableColumnNome;
	
	@FXML
	private TableColumn<Ticket, String> tableColumnClient;
	
	@FXML
	private TableColumn<Ticket, String>  tableColumnCnpj;
	
	@FXML
	private TableColumn<Ticket, Date> tableColumnDate;
	
	
	@FXML
	private TableColumn<Ticket, String> tableColumnDescricao;
	
	@FXML
	private Button btNew;
	
	private ObservableList<Ticket> obsList;
	
	@FXML
	public void OnBtAction() {
		loadView("/views/NewTicket.fxml");
	}
	
	public void setTicketService(TicketService service) {
		this.service=service;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		
		
		
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
	
	//iniciar comportamento de coluna para os atributos da classe que sao colunas 
		private void initializeNodes() {
			tableColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
			tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
			tableColumnClient.setCellValueFactory(new PropertyValueFactory<>("cliente"));
			tableColumnCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
			tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("dataTicket"));
			tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
			
			Stage stage = (Stage) Main.getMainScene().getWindow();
			tableViewTicket.prefHeightProperty().bind(stage.heightProperty());

			


		}
		
		public void updateTableView() {
			if(service == null) {
				throw new IllegalStateException("Serviço vazio");
			}
			List<Ticket> list = service.findAll();
			obsList = FXCollections.observableArrayList(list);
			tableViewTicket.setItems(obsList);
			
		}

		
		}
