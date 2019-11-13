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

	private void start() throws IOException {
		pane.getChildren().clear();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
		this.pane.getChildren().setAll(pane);
	}

	@FXML
	void btnAngeboteClicked(ActionEvent event) throws IOException {
		pane.getChildren().clear();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Angebote.fxml"));
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
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Piloten.fxml"));
		this.pane.getChildren().setAll(pane);
	}

	@FXML
	void btnFluegeClicked(ActionEvent event) throws IOException {
		pane.getChildren().clear();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/CreateFlug.fxml"));
		this.pane.getChildren().setAll(pane);
	}
}
