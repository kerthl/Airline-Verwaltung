package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import bll.*;
import dal.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreateFlugController implements Initializable {

	@FXML
	ComboBox<Pilot> cbCpt;
	@FXML
	ComboBox<Pilot> cbFO;
	@FXML
	ComboBox<Flugzeug> cbFlugzeug;
	@FXML
	DatePicker dpDate;
	@FXML
	ComboBox<Angebot> cbAngebot;
	@FXML
	Label lbFehler;

	ObservableList<Angebot> sAngebote = FXCollections.observableArrayList();
	ObservableList<Pilot> sPiloten = FXCollections.observableArrayList();
	ObservableList<Flugzeug> sFlugzeuge = FXCollections.observableArrayList();

	Flug momentan;
	Boolean finished = false;

	@FXML
	public void finishOk(ActionEvent ev) {
		if (checkIfCompleted()) {
			if (checkPilots()) {
				Flug newFlug = createNewFlug();
				Stage s = (Stage) ((Button) ev.getSource()).getScene().getWindow();
				s.close();
				DataManager.getInstance().createFlug(newFlug);
				
			} else {
				this.lbFehler.setText("Ein Pilot kann nicht Pilot und Co-Pilot gleichzeitig sein!");
			}
		} else {
			this.lbFehler.setText("Bitte füllen Sie alle Felder aus!");
		}

	}

	private Flug createNewFlug() {
		Angebot a = this.cbAngebot.getSelectionModel().getSelectedItem();
		Pilot p1 = this.cbCpt.getSelectionModel().getSelectedItem();
		Pilot p2 = this.cbFO.getSelectionModel().getSelectedItem();
		Flugzeug fz = this.cbFlugzeug.getSelectionModel().getSelectedItem();
		
		
		String europeanDatePattern = "dd.MM.yyyy";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		
		String datum = this.dpDate.getValue().format(europeanDateFormatter);
		Flug flug = new Flug(a, datum, p1, p2, fz);
		return flug;

	}

	private boolean checkPilots() {
		// TODO Auto-generated method stub
		return this.cbCpt.getSelectionModel().getSelectedItem().getId() != this.cbFO.getSelectionModel()
				.getSelectedItem().getId();
	}

	private boolean checkIfCompleted() {
		return cbAngebot.getSelectionModel().getSelectedItem() != null
				&& cbFO.getSelectionModel().getSelectedItem() != null
				&& cbCpt.getSelectionModel().getSelectedItem() != null
				&& cbCpt.getSelectionModel().getSelectedItem() != null && this.dpDate.getValue() != null;

	}

	@FXML
	public void finishCancel(ActionEvent ev) {
		Stage s = (Stage) ((Button) ev.getSource()).getScene().getWindow();
		s.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.sPiloten.clear();
		this.sFlugzeuge.clear();
		this.sAngebote.clear();
		try {
			this.sAngebote.addAll(DataManager.getInstance().getAngebote());
			this.cbAngebot.setItems(this.sAngebote);
			this.sPiloten.addAll(DataManager.getInstance().getPiloten());
			this.cbCpt.setItems(this.sPiloten);
			this.cbFO.setItems(this.sPiloten);
			this.sFlugzeuge.addAll(DataManager.getInstance().getFlugzeuge());
			this.cbFlugzeug.setItems(this.sFlugzeuge);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.getPiloten();
		//this.getAngebote();
		//this.getFlugzeuge();

	}

	private void getFlugzeuge() {
		this.sFlugzeuge.clear();
		Flughafen f = new Flughafen(-99, "Wien", "VIE", "Vienna");
		Flughafen f2 = new Flughafen(-15, "Klagenfurt", "KLU", "KLu");
		Airline ai = new Airline(1, f, "Austria Airlines");
		this.sFlugzeuge.add(new Flugzeug(1, ai, "A320", 2000, 250, "OE-LBU", 35, 6));
		this.cbFlugzeug.setItems(this.sFlugzeuge);
		
		
	}

	private void getPiloten() {
		this.sPiloten.clear();
		try {
			//this.sPiloten.addAll(DataManager.getInstance().getPiloten());
			
			Flughafen f = new Flughafen(-99, "Wien", "VIE", "Vienna");
			Flughafen f2 = new Flughafen(-15, "Klagenfurt", "KLU", "KLu");
			Airline ai = new Airline(1, f, "Austria Airlines");
			Pilot p = new Pilot(-99, ai, "Franz", "Ferdinand", "Airbus-A320");
			Pilot p1 = new Pilot(-99, ai, "Gustav", "Gans", "Airbus-A320");
			
			this.sPiloten.add(p1);
			this.sPiloten.add(p);
			this.cbCpt.setItems(sPiloten);
			this.cbFO.setItems(sPiloten);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getAngebote() {
		this.sAngebote.clear();
		try {
			//this.sAngebote.addAll(DataManager.getInstance().getAngebote());
			
			Flughafen f = new Flughafen(-99, "Wien", "VIE", "Vienna");
			Flughafen f2 = new Flughafen(-15, "Klagenfurt", "KLU", "KLu");
			Airline ai = new Airline(1, f, "Austria Airlines");
			Pilot p = new Pilot(-99, ai, "Lukas", "Kerth", "Airbus-A320");
			Angebot a = new Angebot("OS-100", ai, f, f2, "14 Uhr", "16>Uhr");

			this.sAngebote.add(a);
			
			this.cbAngebot.setItems(sAngebote);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
