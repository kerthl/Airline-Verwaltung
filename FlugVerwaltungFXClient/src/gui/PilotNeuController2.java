package gui;

import java.net.URL;
import java.util.ResourceBundle;

import bll.Airline;
import bll.Pilot;
import dal.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PilotNeuController2 implements Initializable {
	@FXML
	private ComboBox<Airline> cbAirline;
	@FXML
	private Label lbAirline;
	@FXML
	private TextField tfVorname;
	@FXML
	private Label lbNachnahme;
	@FXML
	private TextField tfNachname;
	@FXML
	private Label lbLizenz;
	@FXML
	private TextField tfLizenz;
	@FXML
	private Button btnAddPilot;
	@FXML
	private Button btnCancelPilot;

	DataManager dm;
	ObservableList<Airline> sAirline = FXCollections.observableArrayList();
	Pilot current;
	boolean update = false;

	@FXML
	public void airlineselected(ActionEvent event) {
	}

	@FXML
	public void btnAddPilotClicked(ActionEvent event) {
		Pilot p = createPiloti();
		Stage s = (Stage) ((Button) event.getSource()).getScene().getWindow();
		s.close();
		if(this.update == false) {
		DataManager.getInstance().createPilot(p);
		}else if (this.update == true) {
			DataManager.getInstance().updatePilot(p);
		}
	}

	private Pilot createPiloti() {
		Airline a = this.cbAirline.getSelectionModel().getSelectedItem();
		String vn = this.tfVorname.getText().toString();
		String nn = this.tfNachname.getText().toString();
		String linz = this.tfLizenz.getText().toString();

		Pilot p = new Pilot(a, vn, nn, linz);
		return p;

	}

	@FXML
	public void btnCancelPilotClicked(ActionEvent event) {
		Stage s = (Stage) ((Button) event.getSource()).getScene().getWindow();
		s.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			this.sAirline.addAll(DataManager.getInstance().getAirlines());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.cbAirline.setItems(this.sAirline);

	}

	public void setPilot(Pilot p) {
		current = p;
		setProps();

	}

	private void setProps() {
		cbAirline.getSelectionModel().select(current.getAirline());
		this.tfVorname.setText(current.getVorname());
		this.tfNachname.setText(current.getNachname());
		this.tfLizenz.setText(current.getLizenz());
		this.update = true;
	}

}
