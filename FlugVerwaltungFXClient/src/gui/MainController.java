package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {

	@FXML
	private AnchorPane pane;

	@FXML
	private Button btnHome;

	@FXML
	private Button btnFlugzeuge;

	@FXML
	private Button btnPiloten;

	@FXML
	private Button btnAngebote;

	@FXML
	private Button btnFluege;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void MainController() {
		
	}

	private void start() throws IOException {
		pane.getChildren().clear();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
		this.pane.getChildren().setAll(pane);
	}

	@FXML
	void btnAngeboteClicked(ActionEvent event) throws IOException {
		pane.getChildren().clear();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/AngeboteView.fxml"));
		this.pane.getChildren().setAll(pane);
	}

	@FXML
	void btnFlugzeugeClicked(ActionEvent event) throws IOException {
		pane.getChildren().clear();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Flugzeuge.fxml"));
		this.pane.getChildren().setAll(pane);
	}

	@FXML
	void btnHomeClicked(ActionEvent event) throws IOException {
		pane.getChildren().clear();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
		this.pane.getChildren().setAll(pane);
	}

	@FXML
	void btnPilotenClicked(ActionEvent event) throws IOException {
		pane.getChildren().clear();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Pilot.fxml"));
		this.pane.getChildren().setAll(pane);
	}

	@FXML
	void btnFluegeClicked(ActionEvent event) throws IOException {
		pane.getChildren().clear();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/TatsFlugView.fxml"));
		this.pane.getChildren().setAll(pane);
	}
	
	public void alleFlugzeuge() throws IOException {
		pane.getChildren().clear();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Flugzeuge.fxml"));
		this.pane.getChildren().setAll(pane);
	}
}
