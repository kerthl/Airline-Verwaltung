package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import bll.Flug;
import dal.DataManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable{
	
	@FXML
	ListView<Flug> lvFluege;
	
	//List<Flug> fluege = new ArrayList<Flug>();
	
	DataManager dm;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
		
			dm = new DataManager();
			
			fillList();
		
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	@FXML
	public void lvFlugClicked(MouseEvent ev) throws IOException, SQLException {
		if (ev.getClickCount() == 2) {
			
			Flug f = lvFluege.getSelectionModel().getSelectedItem();
			
			showFlugGUI(f);
		}
	}
	
	private void showFlugGUI(Flug f) throws IOException {
		BorderPane root = null;
		Scene scene = null;
		FXMLLoader loader = null;

		Stage stage = new Stage();
		FlugController flugController = null;
		
		loader = new FXMLLoader(getClass().getResource(("/gui/Flug.fxml")));
		root = loader.load();
		flugController = loader.getController();

		if (f != null) {
			flugController.setFlug(f);
		}

		scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("/gui/application.css").toExternalForm());
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}
	
	private void fillList() throws Exception {
		//this.fluege = dm.getAllFluege();

	
		ObservableList<Flug> observableList = FXCollections.observableArrayList(dm.getAllFluege());

		this.lvFluege.setItems(observableList);

	}
}
