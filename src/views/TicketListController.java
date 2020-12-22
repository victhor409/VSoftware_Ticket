package views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entities.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.service.TicketService;
import util.Utils;

public class TicketListController implements Initializable, DataChangeListene {

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
	private TableColumn<Ticket, String> tableColumnCnpj;

	@FXML
	private TableColumn<Ticket, Date> tableColumnDate;

	@FXML
	private TableColumn<Ticket, String> tableColumnDescricao;

	@FXML
	private Button btNew;

	private ObservableList<Ticket> obsList;

	

	@FXML
	public void OnBtAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Ticket obj = new Ticket();
		createDialogForm(obj, "/views/NewTicket.fxml", parentStage);
	}

	public void setTicketService(TicketService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();

	}

	private void createDialogForm(Ticket obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			NewTicketController controller = loader.getController();
			controller.setTicket(obj);
			controller.setTicketService(new TicketService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Novo Ticket");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	// iniciar comportamento de coluna para os atributos da classe que sao colunas
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
		if (service == null) {
			throw new IllegalStateException("Serviço vazio");
		}
		List<Ticket> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewTicket.setItems(obsList);
		
	}

	@Override
	public void onDataChanged() {
		updateTableView();

	}

}
