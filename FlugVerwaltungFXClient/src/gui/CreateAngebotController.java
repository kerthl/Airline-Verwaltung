package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import bll.Airline;
import bll.Angebot;
import bll.Flughafen;
import dal.DataManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CreateAngebotController implements Initializable {

	@FXML
	Button done;
	@FXML
	TextField bezeichnung;
	@FXML
	ComboBox<String> cbxDeparture;
	@FXML
	ComboBox<String> cbxArrival;
	@FXML
	TextField departureTime;
	@FXML
	TextField arrivalTime;
	@FXML
	Label label;

	Angebot momentan;
	Boolean update = false;

	DataManager db = DataManager.getInstance();
	ArrayList<String> air = new ArrayList<String>();
	ArrayList<Flughafen> airports = new ArrayList<Flughafen>();
	Airline airline = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			// this.airline = db.getAirline(1);

			airports = db.getAirports();
			airports.forEach(port -> air.add(port.getBezeichnung()));
			cbxDeparture.getItems().setAll(air);
			cbxArrival.getItems().setAll(air);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		departureTime.focusedProperty().addListener((arg2, oldValue, newValue) -> {
			if (!newValue) {
				if (!departureTime.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
					departureTime.setText("");
				}
			}
		});

		arrivalTime.focusedProperty().addListener((arg22, oldValue2, newValue2) -> {
			if (!newValue2) {
				if (!arrivalTime.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
					arrivalTime.setText("");
				}
			}
		});
	}

	public void doneClicked() {
		try {
			String errorMsg = "Bitte wählen Sie ";
			Boolean error = false;

			String bez = bezeichnung.getText();

			int tempDep = cbxDeparture.getSelectionModel().getSelectedIndex();
			Flughafen depAirport = null;
			if (tempDep >= 0) {
				depAirport = airports.get(tempDep);
			}
			int tempArr = cbxArrival.getSelectionModel().getSelectedIndex();
			Flughafen arrAirport = null;
			if (tempArr >= 0) {
				arrAirport = airports.get(tempArr);
			}

			String depTime = departureTime.getText();
			String arrTime = arrivalTime.getText();
			if (bez.equals("")) {
				bezeichnung.setStyle("-fx-border-color: red;");
				errorMsg += "Bezeichnung, ";
				error = true;
			} else {
				bezeichnung.setStyle("-fx-border-color: gray;");
			}
			if (depAirport == null) {
				cbxDeparture.setStyle("-fx-border-color: red;");
				errorMsg += "Abflugflughafen, ";
				error = true;
			} else {
				cbxDeparture.setStyle("-fx-border-color: gray;");
			}
			if (arrAirport == null) {
				cbxArrival.setStyle("-fx-border-color: red;");
				errorMsg += "Ankunftsflughafen, ";
				error = true;
			} else {
				cbxArrival.setStyle("-fx-border-color: gray;");
			}
			if (depTime.equals("")) {
				departureTime.setStyle("-fx-border-color: red;");
				errorMsg += "Abflugszeit, ";
				error = true;
			} else {
				departureTime.setStyle("-fx-border-color: gray;");
			}
			if (arrTime.equals("")) {
				arrivalTime.setStyle("-fx-border-color: red;");
				errorMsg += "Ankunftszeit, ";
				error = true;
			} else {
				arrivalTime.setStyle("-fx-border-color: gray;");
			}

			if (error) {
				errorMsg = errorMsg.substring(0, errorMsg.length() - 2);
				label.setText(errorMsg);
			} else {
				// String id, Airline airline, Flughafen flughafenAb, Flughafen flughafenAn,
				// String zeiten
				Angebot angebot = new Angebot(bez, airline, depAirport, arrAirport, depTime, arrTime);
				System.out.println(angebot);
				label.setTextFill(Color.web("#01DF01"));
				if (update) {
					db.updateAngebot(angebot);
					label.setText("Angebot erfolgreich aktualisiert");
				} else {
					db.addAngebot(angebot);
					label.setText("Angebot erfolgreich angelegt");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setAngebot(Angebot a) {
		this.momentan = a;
		try {
			this.bezeichnung.setText(this.momentan.getFlugNummer());
			this.cbxDeparture.getSelectionModel().select(this.momentan.getFlughafenAb().getBezeichnung());
			this.cbxArrival.getSelectionModel().select(this.momentan.getFlughafenAn().getBezeichnung());
			this.departureTime.setText(this.momentan.getAbflugsZeit());
			this.departureTime.setText(this.momentan.getAnkunftsZeit());
			update = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
