package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import bll.Airline;
import bll.Flugzeug;
import dal.DataManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class FlugzeugeController implements Initializable {

	@FXML
	private TableView<Flugzeug> tvFlugzeuge;

	@FXML
	private Button btnNeuesFlugzeug;

	@FXML
	private ComboBox<Airline> cbAirline;

	@FXML
	private TextField txtBezeichnung;

	@FXML
	private TextField txtSitze;

	@FXML
	private TextField txtBaujahr;

	@FXML
	private TextField txtKennzeichen;

	@FXML
	private TextField txtReihen;

	@FXML
	private TextField txtSitzeReihe;
	
	Button btnNeu;
	Button btnZurueck;
	
	AnchorPane pane;

	TableColumn<Flugzeug, Integer> id = null;
	TableColumn<Flugzeug, Airline> airline = null;
	TableColumn<Flugzeug, String> bezeichnung = null;
	TableColumn<Flugzeug, Integer> maxSitze = null;
	TableColumn<Flugzeug, Integer> baujahr = null;
	TableColumn<Flugzeug, String> kennzeichen = null;
	TableColumn<Flugzeug, Integer> anzahlR = null;
	TableColumn<Flugzeug, Integer> anzahlSitzeProR = null;

	DataManager dm;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			dm = DataManager.getInstance();
			GenerateColumns();
			//fillAirlines();
			fillFlugzeuge();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void btnNeuesFlugzeugClicked(ActionEvent event) throws Exception {
		if (!txtBaujahr.getText().trim().isEmpty() || !txtBezeichnung.getText().trim().isEmpty()
				|| !txtKennzeichen.getText().trim().isEmpty() || !txtReihen.getText().trim().isEmpty()
				|| !txtSitze.getText().trim().isEmpty() || !txtSitzeReihe.getText().trim().isEmpty()) {
			Flugzeug neu = generateNewFlugzeug();
			dm.addFlugzeug(neu);
			fillFlugzeuge();
			setAllEmpty();
		} else {

		}
	}

	private void setAllEmpty() {
		txtBaujahr.setText("");
		txtBezeichnung.setText("");
		txtKennzeichen.setText("");
		txtReihen.setText("");
		txtSitze.setText("");
		txtSitzeReihe.setText("");
	}

	private Flugzeug generateNewFlugzeug() {
		Airline a = cbAirline.getSelectionModel().getSelectedItem();
		String bez = txtBezeichnung.getText();
		int ms = Integer.parseInt(txtSitze.getText());
		int bauj = Integer.parseInt(txtBaujahr.getText());
		String kennz = txtKennzeichen.getText();
		int ar = Integer.parseInt(txtReihen.getText());
		int asr = Integer.parseInt(txtSitzeReihe.getText());

		return new Flugzeug(-99, a, bez, ms, bauj, kennz, ar, asr);
	}

	public void fillAirlines() throws Exception {
		List<Airline> alleAirlines = dm.getAirlines();
		cbAirline.setItems(FXCollections.observableArrayList(alleAirlines));
		cbAirline.getSelectionModel().selectFirst();
	}

	private void fillFlugzeuge() throws Exception {
		List<Flugzeug> alleFZ = dm.getFlugzeuge();
		tvFlugzeuge.setItems(FXCollections.observableArrayList(alleFZ));
	}

	@SuppressWarnings("unchecked")
	public void GenerateColumns() {
		id = new TableColumn<Flugzeug, Integer>("ID");
		airline = new TableColumn<Flugzeug, Airline>("Airline");
		bezeichnung = new TableColumn<Flugzeug, String>("Bezeichnung");
		maxSitze = new TableColumn<Flugzeug, Integer>("Sitze");
		baujahr = new TableColumn<Flugzeug, Integer>("Baujahr");
		kennzeichen = new TableColumn<Flugzeug, String>("Kennzeichen");
		anzahlR = new TableColumn<Flugzeug, Integer>("Reihen");
		anzahlSitzeProR = new TableColumn<Flugzeug, Integer>("S/R");

		id.setCellValueFactory(new PropertyValueFactory<Flugzeug, Integer>("id"));
		airline.setCellValueFactory(new PropertyValueFactory<Flugzeug, Airline>("airline"));
		bezeichnung.setCellValueFactory(new PropertyValueFactory<Flugzeug, String>("bezeichnung"));
		maxSitze.setCellValueFactory(new PropertyValueFactory<Flugzeug, Integer>("maxSitze"));
		baujahr.setCellValueFactory(new PropertyValueFactory<Flugzeug, Integer>("baujahr"));
		kennzeichen.setCellValueFactory(new PropertyValueFactory<Flugzeug, String>("kennzeichen"));
		anzahlR.setCellValueFactory(new PropertyValueFactory<Flugzeug, Integer>("anzahlReihen"));
		anzahlSitzeProR.setCellValueFactory(new PropertyValueFactory<Flugzeug, Integer>("anzahlSitzeProReihe"));

		this.tvFlugzeuge.getColumns().addAll(id, airline, bezeichnung, maxSitze, baujahr, kennzeichen, anzahlR,
				anzahlSitzeProR);
	}

	@FXML
	void btnNeuClicked(ActionEvent event) throws Exception {

	}
	
	@FXML
	void btnZurueckClicked(ActionEvent event) throws Exception {
		pane.getChildren().clear();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Flugzeuge.fxml"));
		this.pane.getChildren().setAll(pane);
	}
}
