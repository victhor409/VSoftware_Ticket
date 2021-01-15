package views;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import DB.DbException;
import entities.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.exception.ValidationException;
import model.service.TicketService;
import util.Alerts;
import util.Constraints;
import util.Utils;

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

	private Ticket entity;

	private TicketService service;
	
	private List<DataChangeListene> dataChangeListeners = new ArrayList<>();
	
	
	public void setTicket(Ticket entity) {
		this.entity = entity;
	}

	public void setTicketService(TicketService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListene listener) {
		dataChangeListeners.add(listener);
	}
	

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}

		if (service == null) {
			throw new IllegalStateException("Service was null");
		}

		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		} catch (ValidationException e) {
			e.getMessage();
		} catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void notifyDataChangeListeners() {
		for (DataChangeListene listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private Ticket getFormData() {

		Ticket obj = new Ticket();

		ValidationException exception = new ValidationException("Validation error");



		if (txName.getText() == null || txName.getText().trim().equals("")) {
			exception.addError("name", "Field can't be empty");
		}
		obj.setNome(txName.getText());

		if (txClient.getText() == null || txClient.getText().trim().equals("")) {
			exception.addError("Cliente", "Field can't be empty");
		}
		obj.setCliente(txClient.getText());

		if (txCnpj.getText() == null || txCnpj.getText().trim().equals("")) {
			exception.addError("cnpj", "Field can't be empty");
		}
		obj.setCnpj(txCnpj.getText());
		
		if(txDate.getText()==null || txDate.getText().trim().equals("")) {
			exception.addError("dataTicket", "Field can't be empty");
		}
		obj.setDataTicket(txDate.getText());
		
		
		
		if (txDescription.getText() == null || txDescription.getText().trim().equals("")) {
			exception.addError("descricao", "Field can't be empty");
		}
		obj.setDescricao(txDescription.getText());

		if (exception.getErrors().size() > 0) {
			throw exception;
		}
		

		return obj;
	}

	

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializaNodes();
	}

	private void initializaNodes() {

		Constraints.setTextFieldMaxLength(txName, 30);
		Constraints.setTextFieldMaxLength(txClient, 30);
		Constraints.setTextFieldMaxLength(txCnpj, 30);

		

	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity is null");
		}

		txName.setText(entity.getNome());
		txClient.setText(entity.getCliente());
		txCnpj.setText(entity.getCnpj());
		txDate.setText(entity.getDataTicket());
		txDescription.setText(entity.getDescricao());

	}

	
}