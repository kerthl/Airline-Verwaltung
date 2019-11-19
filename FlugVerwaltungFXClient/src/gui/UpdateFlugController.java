package gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import bll.*;
import dal.DataManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateFlugController implements Initializable {

	@FXML
	ComboBox<Pilot> cbCpt;
	@FXML
	ComboBox<Pilot> cbFO;
	@FXML
	ComboBox<Flugzeug> cbFlugzeug;
	@FXML
	DatePicker dpDate;
	@FXML
	Label lbAngebot;
	@FXML
	Label label;

	ObservableList<Pilot> sPiloten = FXCollections.observableArrayList();
	ObservableList<Flugzeug> sFlugzeuge = FXCollections.observableArrayList();

	Flug momentan;
	Boolean finished = false;

	public void setFlug(Flug f) {
		this.momentan = f;
		try {			
			this.cbCpt.getSelectionModel().select(this.momentan.getCaptain());
			this.cbFO.getSelectionModel().select(this.momentan.getFirstOfficer());
			this.cbFlugzeug.getSelectionModel().select(this.momentan.getFlugzeug());
			this.lbAngebot.setText(this.momentan.getAngebot().toString());
			String [] date = this.momentan.getDatum().split(".");
			System.out.println(date);
			this.dpDate.setValue(LocalDate.of(Integer.parseInt(date[0]),Month.valueOf(date[1]),Integer.parseInt(date[2])));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void finishOk(ActionEvent ev) {
		if (checkIfCompleted()) {
			if (checkPilots()) {
				updateFlug();
				DataManager.getInstance().updateFlug(this.momentan);
			} else {
				this.label.setText("Ein Pilot kann nicht Pilot und Co-Pilot gleichzeitig sein!");
			}
		} else {
			this.label.setText("Bitte füllen Sie alle Felder aus!");
		}

	}

	private void updateFlug() {
		this.momentan.setCaptain(this.cbCpt.getSelectionModel().getSelectedItem());
		this.momentan.setFirstOfficer(this.cbFO.getSelectionModel().getSelectedItem());
		this.momentan.setFlugzeug(this.cbFlugzeug.getSelectionModel().getSelectedItem());
		/*	
		LocalDate ld = this.dpDate.getValue();
		Calendar c = Calendar.getInstance();
		c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
		Date date = c.getTime();
		*/

		this.momentan.setDatum(this.momentan.getDatum());

	}

	private boolean checkPilots() {
		// TODO Auto-generated method stub
		return this.cbCpt.getSelectionModel().getSelectedItem().getId() != this.cbFO.getSelectionModel()
				.getSelectedItem().getId();
	}

	private boolean checkIfCompleted() {
		return cbFO.getSelectionModel().getSelectedItem() != null && cbCpt.getSelectionModel().getSelectedItem() != null
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
		try {
			this.sPiloten.addAll(DataManager.getInstance().getPiloten());
			this.cbCpt.setItems(this.sPiloten);
			this.cbFO.setItems(this.sPiloten);
			this.sFlugzeuge.addAll(DataManager.getInstance().getFlugzeuge());
			this.cbFlugzeug.setItems(this.sFlugzeuge);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
