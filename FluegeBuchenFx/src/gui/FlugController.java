package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bll.Flug;
import dal.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FlugController implements Initializable{
	  @FXML
	    private Label lblAngebot;

	    @FXML
	    private Label lblDatum;

	    @FXML
	    private Label lblCaptain;

	    @FXML
	    private Label lblFOfficer;

	    @FXML
	    private Label lblFlugzeug;
	    
	    @FXML
	    private Label lblSitzplaetze;

	    @FXML
	    private Label lblGebucht;

	    @FXML
	    private Button btnZurueck;

	    @FXML
	    private Button btnBuchen;
	    
	    Flug f = null;

	    DataManager dm;
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			dm = new DataManager();
		}

	    @FXML
	    void btnBuchenClicked(ActionEvent event) throws IOException {
	    	BorderPane root = null;
			Scene scene = null;
			FXMLLoader loader = null;

			Stage stage = new Stage();
			PassagierController passagierController = null;
			
			loader = new FXMLLoader(getClass().getResource(("/gui/Passagier.fxml")));
			root = loader.load();
			passagierController = loader.getController();

			if (f != null) {
				passagierController.setFlug(f);
			}

			scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("/gui/application.css").toExternalForm());
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
	    }

	    @FXML
	    void btnZurueckClicked(ActionEvent event) {
	    	Stage s = (Stage) ((Button) event.getSource()).getScene().getWindow();
			s.close();
	    }


		public void setFlug(Flug f) {
			this.f = f;

			this.lblAngebot.setText(f.getAngebotInfo());
			this.lblCaptain.setText(f.getCptInfo());
			this.lblDatum.setText(f.getDatum());
			this.lblFlugzeug.setText(f.getFlugzeugInfo());
			this.lblFOfficer.setText(f.getFoInfo());
			this.lblSitzplaetze.setText(Integer.toString(f.getFlugzeug().getMaxSitze()));
			this.lblGebucht.setText(Integer.toString(dm.getAnzahlFlugBuchungen(f.getAngebot().getFlugNummer())));
			
		}

	
}
