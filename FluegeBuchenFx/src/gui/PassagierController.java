package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import bll.Fliegt;
import bll.Flug;
import bll.Passagier;
import dal.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PassagierController implements Initializable {

	@FXML
	private Button btnZurueck;

	@FXML
	private Button btnBuchen;

	@FXML
	private TextField txtVorname;

	@FXML
	private TextField txtNachname;

	@FXML
	private TextField txtGebDatum;

	@FXML
	private TextField txtStrasse;

	@FXML
	private TextField txtOrt;

	@FXML
	private TextField txtPlz;

	DataManager dm;

	Flug f = null;

	Fliegt fl = null;
	
	Date sqlDate;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dm = new DataManager();
	}

	public void setFlug(Flug f) {
		this.f = f;
	}

	@FXML
	void btnBuchenClicked(ActionEvent event) throws IOException {
		Passagier p = generatePassagier();
		fl = new Fliegt(p, f.getAngebot().getFlugNummer(), f.getDatum(), "7A", 35);
		
		dm.setPassagier(p);
		dm.setFliegt(fl);

		if(f != null && fl != null)
			showBoardingKarte();
	}

	private Passagier generatePassagier() {
		Passagier p = new Passagier();
		// long millis = System.currentTimeMillis();

		Date date = sqlDate.valueOf(LocalDate.now());

		p.setVorname(txtVorname.getText().toString());
		p.setNachname(txtNachname.getText().toString());
		p.setGebDatum(null);
		p.setStrasse(txtStrasse.getText().toString());
		p.setOrt(txtOrt.getText().toString());
		p.setPlz(Integer.parseInt(txtPlz.getText()));
		System.out.print(p.toString());
		return p;
	}

	@FXML
	void btnZurueckClicked(ActionEvent event) {
		Stage s = (Stage) ((Button) event.getSource()).getScene().getWindow();
		s.close();
	}
	
	private void showBoardingKarte() throws IOException {
		AnchorPane root = null;
		Scene scene = null;
		FXMLLoader loader = null;

		Stage stage = new Stage();
		BoardingKarteController bKarteController = null;
		
		loader = new FXMLLoader(getClass().getResource(("/gui/BoardingKarte.fxml")));
		root = loader.load();
		bKarteController = loader.getController();

		if(f != null && fl != null) {
			bKarteController.setFliegt(this.fl);
			bKarteController.setFlug(this.f);
			bKarteController.setBoardingKarte();
			
		}
		scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("/gui/application.css").toExternalForm());
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}
}
