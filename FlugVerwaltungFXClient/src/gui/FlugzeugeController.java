package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import bll.Airline;
import bll.Flugzeug;
import dal.DataManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FlugzeugeController implements Initializable {

	@FXML
	private TableView<Flugzeug> tvFlugzeuge;
	
	@FXML
	private Button btnNeu;
	
	@FXML
	private Button btnLoeschen;

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
			
			fillFlugzeuge();
			
			tvFlugzeuge.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent click) {
					if(click.getClickCount() == 2) {
						try {
							updateFlugzeug();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		Stage stage = new Stage();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/gui/FlugzeugNeu.fxml"));
		Scene scene = new Scene(root);	
		stage.setScene(scene);
		stage.setTitle("Neues Flugzeug anlegen");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		this.fillFlugzeuge();	
	}
	
	@FXML
	void btnLoeschenClicked(ActionEvent event) throws Exception {
		if(tvFlugzeuge.getSelectionModel().getSelectedItem() != null)
			{
				dm.deleteFlugzeug(tvFlugzeuge.getSelectionModel().getSelectedItem());
				this.fillFlugzeuge();
			}
	}
	
	private void updateFlugzeug() throws Exception {
		FlugzeugBearbeitenController fbc = new FlugzeugBearbeitenController();
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FlugzeugBearbeiten.fxml"));
		loader.setController(fbc);
		AnchorPane root = loader.load();
		fbc.setFlugzeug(tvFlugzeuge.getSelectionModel().getSelectedItem());
		Scene scene = new Scene(root);	
		stage.setScene(scene);
		stage.setTitle("Flugzeug bearbeiten");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		this.fillFlugzeuge();	
	}
}
