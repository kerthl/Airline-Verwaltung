package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import bll.Pilot;
import dal.DataManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PilotController implements Initializable{
	@FXML
	private AnchorPane aPAddPilot;
	@FXML
	private TableView tvPilotList;
	@FXML
	private TableColumn colAirline;
	@FXML
	private TableColumn colVorname;
	@FXML
	private TableColumn colNachname;
	@FXML
	private TableColumn colLizenz;
	@FXML
	private MenuItem ctxDelete;
	@FXML
	private Button btnNeu;
	
	DataManager dm;

	@FXML
	public void deleteClicked(ActionEvent event) {
		
	}
	
	@FXML
	public void btnNeuerPilotClicked(ActionEvent event) throws Exception {
		Stage stage = new Stage();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/gui/PilotNeu.fxml"));
		Scene scene = new Scene(root);	
		stage.setScene(scene);
		stage.setTitle("Neues Piloti anlegen");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		this.fillPilotie();	
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			dm = DataManager.getInstance();
			GenerateColumns();
			
			fillPilotie();
			
			tvPilotList.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent click) {
					if(click.getClickCount() == 2) {
						try {
							updatePiloti();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void fillPilotie() throws Exception {
		List<Pilot> pilotis = dm.getPiloten();
		tvPilotList.setItems(FXCollections.observableArrayList(pilotis));
	}
	
	private void updatePiloti() throws Exception {
		PilotNeuController2 fbc = new PilotNeuController2();
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/PilotNeu.fxml"));
		
		AnchorPane root = loader.load();
		fbc.setPilot((Pilot)tvPilotList.getSelectionModel().getSelectedItem());
		Scene scene = new Scene(root);	
		stage.setScene(scene);
		stage.setTitle("Piloti bearbeiten");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		this.fillPilotie();	
	}
	
	private void GenerateColumns() {
		colVorname = new TableColumn<Pilot, String>("Vorname");
		colNachname = new TableColumn<Pilot, String>("Nachname");
		colLizenz = new TableColumn<Pilot, String>("Lizenz");

		colVorname.setCellValueFactory(new PropertyValueFactory<Pilot, String>("vorname"));
		colNachname.setCellValueFactory(new PropertyValueFactory<Pilot, String>("nachname"));
		colLizenz.setCellValueFactory(new PropertyValueFactory<Pilot, String>("lizenz"));
		

		this.tvPilotList.getColumns().addAll(colVorname, colNachname, colLizenz);
	}
}
