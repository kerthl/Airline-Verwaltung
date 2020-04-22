package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import bll.Fliegt;
import bll.Flug;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BoardingKarteController implements Initializable{
    @FXML
    private ImageView imgKarte;
    
    @FXML
    private ImageView imgVirus;

    @FXML
    private Button btnSchliessen;
    
    @FXML
    private Label lblName;

    @FXML
    private Label lblOrt;

    @FXML
    private Label lblZeit;

    @FXML
    private Label lblSP;

    @FXML
    private Label lblFNr;

    @FXML
    private Label lblPreis;
    
    Fliegt fliegt;
    Flug flug;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setImage();
		
	}
    
    @FXML
    void btnSchliessenClicked(ActionEvent event) {
    	Stage s = (Stage) ((Button) event.getSource()).getScene().getWindow();
		s.close();
    }

    private void setImage() {
    	File file = new File("BoardingCard.jpg");
        Image image = new Image(file.toURI().toString());
        imgKarte.setImage(image);
        
        File fileVirus = new File("muhaha");
        Image imageVirus = new Image(fileVirus.toURI().toString());
        imgVirus.setImage(imageVirus);
    }
	
    public void setFliegt(Fliegt fl) {
    	this.fliegt = fl;
    }
    
    public void setFlug(Flug f) {
    	this.flug = f;
    	
    }
    
    public void setBoardingKarte() {
    	if(this.fliegt != null && this.flug != null) {
    		this.lblName.setText(fliegt.getPassagier().getVorname().toString() + " " + fliegt.getPassagier().getNachname().toString());
        	this.lblOrt.setText(flug.getAngebot().getFlughafenAb().getOrt().toString() + "       -     " + flug.getAngebot().getFlughafenAn().getOrt().toString());
          	this.lblZeit.setText(flug.getAngebot().getAbflugszeit().toString() + "       -     " + flug.getAngebot().getAnkunftszeit().toString());
          	this.lblFNr.setText(fliegt.getFlugNr().toString());
          	this.lblPreis.setText(Integer.toString(fliegt.getPreis()) + " €");
          	this.lblSP.setText(fliegt.getSitzplatz().toString());

    	}	
    }
}
