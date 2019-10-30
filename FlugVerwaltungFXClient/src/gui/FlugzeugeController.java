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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FlugzeugeController implements Initializable{

    @FXML
    private TableView<Flugzeug> tvFlugzeuge;

    @FXML
    private Button btnNeuesFlugzeug;
    
    @FXML
    private JFXComboBox<Airline> cbAirline;

    @FXML
    private JFXTextField txtBezeichnung;

    @FXML
    private JFXTextField txtSitze;

    @FXML
    private JFXTextField txtBaujahr;

    @FXML
    private JFXTextField txtKennzeichen;

    @FXML
    private JFXTextField txtReihen;

    @FXML
    private JFXTextField txtSitzeReihe;

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
		dm = DataManager.getInstance();
		GenerateColumns();
    	/*try {
			fillFlugzeuge();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
    
    @FXML
    void btnNeuesFlugzeugClicked(ActionEvent event) {

    }

	private void fillFlugzeuge() throws Exception {
		List<Flugzeug> alleFZ = dm.getFlugzeuge();
		tvFlugzeuge.setItems(FXCollections.observableArrayList(alleFZ));
	}
	
	@SuppressWarnings("unchecked")
	public void GenerateColumns() {
		id = new TableColumn<Flugzeug, Integer>("ID");
		airline = new TableColumn<Flugzeug, Airline>("Airline");
		bezeichnung = new TableColumn<Flugzeug, String>("Bezeihnung");
		maxSitze = new TableColumn<Flugzeug, Integer>("Sitze");
		baujahr = new TableColumn<Flugzeug, Integer>("Baujahr");
		kennzeichen = new TableColumn<Flugzeug, String>("Kennzeichen");
		anzahlR = new TableColumn<Flugzeug, Integer>("Reihen");
		anzahlSitzeProR = new TableColumn<Flugzeug, Integer>("Sitze/Reihe");
		
		id.setCellValueFactory(new PropertyValueFactory<Flugzeug, Integer>("id"));
		airline.setCellValueFactory(new PropertyValueFactory<Flugzeug, Airline>("airline"));
		bezeichnung.setCellValueFactory(new PropertyValueFactory<Flugzeug, String>("bezeichnung"));
		maxSitze.setCellValueFactory(new PropertyValueFactory<Flugzeug, Integer>("maxSitze"));
		baujahr.setCellValueFactory(new PropertyValueFactory<Flugzeug, Integer>("baujahr"));
		kennzeichen.setCellValueFactory(new PropertyValueFactory<Flugzeug, String>("kennzeichen"));
		anzahlR.setCellValueFactory(new PropertyValueFactory<Flugzeug, Integer>("anzahlReihen"));
		anzahlSitzeProR.setCellValueFactory(new PropertyValueFactory<Flugzeug, Integer>("anzahlSitzeProReihe"));
		
		this.tvFlugzeuge.getColumns().addAll(id, airline, bezeichnung, maxSitze, baujahr, kennzeichen, anzahlR, anzahlSitzeProR);
	}

}
