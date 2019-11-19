package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import bll.Airline;
import bll.Flugzeug;
import dal.DataManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FlugzeugBearbeitenController implements Initializable{

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

    @FXML
    private Button btnSpeichern;

    @FXML
    private Button btnZurueck;
    
    Flugzeug current;

    DataManager dm;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dm = DataManager.getInstance();
	}
    
    @FXML
    void btnSpeichernClicked(ActionEvent event) {
    	if (!txtBaujahr.getText().trim().isEmpty() || !txtBezeichnung.getText().trim().isEmpty()
				|| !txtKennzeichen.getText().trim().isEmpty() || !txtReihen.getText().trim().isEmpty()
				|| !txtSitze.getText().trim().isEmpty() || !txtSitzeReihe.getText().trim().isEmpty()) {
			Flugzeug neu = generateNewFlugzeug();
			dm.updateFlugzeug(neu);
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
			stage.close();
		} else {

		}
    }
    
    private Flugzeug generateNewFlugzeug() {
		Airline a = cbAirline.getSelectionModel().getSelectedItem();
		String bez = txtBezeichnung.getText();
		int ms = Integer.parseInt(txtSitze.getText());
		int bauj = Integer.parseInt(txtBaujahr.getText());
		String kennz = txtKennzeichen.getText();
		int ar = Integer.parseInt(txtReihen.getText());
		int asr = Integer.parseInt(txtSitzeReihe.getText());

		return new Flugzeug(current.getId(), a, bez, ms, bauj, kennz, ar, asr);
	}
    
	public void fillAirlines() throws Exception {
		List<Airline> alleAirlines = dm.getAirlines();
		cbAirline.setItems(FXCollections.observableArrayList(alleAirlines));
		cbAirline.getSelectionModel().selectFirst();
	}
	
	@FXML
	void btnZurueckClicked(ActionEvent event) throws Exception {
		Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		stage.close();
	}

	public void setFlugzeug(Flugzeug f) {
		current = f;
		setProp();
	}
	
	private void setProp() {
		cbAirline.getSelectionModel().select(current.getAirline());
		txtBezeichnung.setText(current.getBezeichnung());
		txtSitze.setText(Integer.toString(current.getMaxSitze()));
		txtBaujahr.setText(Integer.toString(current.getBaujahr()));
		txtKennzeichen.setText(current.getKennzeichen());
		txtReihen.setText(Integer.toString(current.getAnzahlReihen()));
		txtSitzeReihe.setText(Integer.toString(current.getAnzahlSitzeProReihe()));
	}
}
