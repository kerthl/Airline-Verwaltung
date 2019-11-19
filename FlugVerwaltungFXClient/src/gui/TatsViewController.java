package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import bll.*;
import dal.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TatsViewController implements Initializable {

	@FXML
	TableView<Flug> tv;
	ObservableList<Flug> sFluege = FXCollections.observableArrayList();

	@FXML
	TableColumn<Flug, String> angebot = null;
	@FXML
	TableColumn<Flug, String> date = null;
	@FXML
	TableColumn<Flug, Pilot> pilot = null;
	@FXML
	TableColumn<Flug, Pilot> ersterO = null;
	@FXML
	TableColumn<Flug, Flugzeug> flugzeug = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.initTableView();
		this.fillFluege();
	}

	private void initTableView() {
		angebot = new TableColumn<Flug, String>("Angebot");
		date = new TableColumn<Flug, String>("Date");
		pilot = new TableColumn<Flug, Pilot>("Kapitän");
		ersterO = new TableColumn<Flug, Pilot>("Erster Offizier");
		flugzeug = new TableColumn<Flug, Flugzeug>("Flugzeug");

		angebot.setCellValueFactory(new PropertyValueFactory<Flug, String>("angebotInfo"));
		date.setCellValueFactory(new PropertyValueFactory<Flug, String>("datum"));
		pilot.setCellValueFactory(new PropertyValueFactory<Flug, Pilot>("cptInfo"));
		ersterO.setCellValueFactory(new PropertyValueFactory<Flug, Pilot>("foInfo"));
		flugzeug.setCellValueFactory(new PropertyValueFactory<Flug, Flugzeug>("flugzeugInfo"));

		this.tv.getColumns().addAll(angebot,date, pilot, ersterO, flugzeug);

		this.tv.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2
						&& tv.getSelectionModel().getSelectedItem() != null) {
					openUpdateDialog();
				}
			}
		});
	}


	private void fillFluege() {
		try {
			sFluege.clear();
			sFluege.addAll(DataManager.getInstance().getFluege());

			/*
			Flughafen f = new Flughafen(-99, "Wien", "VIE", "Vienna");
			Flughafen f2 = new Flughafen(-15, "Klagenfurt", "KLU", "KLu");
			Airline ai = new Airline(1, f, "Austria Airlines");
			Pilot p = new Pilot(-99, ai, "Lukas", "Kerth", "Airbus-A320");
			Angebot a = new Angebot("OS-100", ai, f, f2, "14 Uhr", "16>Uhr");

			Flugzeug fz = new Flugzeug(1, ai, "A320", 2000, 250, "OE-LBU", 35, 6);

			Flug flug = new Flug(a, new Date(0), p, p, fz);

			sFluege.add(flug);
			*/
			this.tv.setItems(sFluege);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void openUpdateDialog() {
		FXMLLoader loader = null;
		BorderPane root = null;
		UpdateFlugController controller = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/gui/UpdateFlug.fxml"));
			root = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage currentStage = new Stage();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		currentStage.setTitle("Update Flug");
		currentStage.setScene(scene);
		currentStage.initModality(Modality.APPLICATION_MODAL);
		controller.setFlug(this.tv.getSelectionModel().getSelectedItem());
		currentStage.showAndWait();
		this.fillFluege();

	}

	@FXML
	public void addFLug() {
		FXMLLoader loader = null;
		BorderPane root = null;
		CreateFlugController controller = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/gui/CreateFlug.fxml"));
			root = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage currentStage = new Stage();
		Scene scene = new Scene(root);
		currentStage.setTitle("Add Flug");
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		currentStage.setScene(scene);
		currentStage.initModality(Modality.APPLICATION_MODAL);
		currentStage.showAndWait();
		this.fillFluege();
	}

	@FXML
	public void deleteFlug() {

	}

}
