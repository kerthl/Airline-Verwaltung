package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import bll.Airline;
import bll.Angebot;
import bll.Flughafen;
import bll.Pilot;
import dal.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AngebotController implements Initializable {

	@FXML
	TableView<Angebot> tv;
	ObservableList<Angebot> angebote = FXCollections.observableArrayList();

	// String flugNummer, Airline airline, Flughafen flughafenAb, Flughafen flughafenAn, String abflugsZeit,
	// String ankunftsZeit
	@FXML
	TableColumn<Angebot, Integer> flugNummer = null;
	@FXML
	TableColumn<Angebot, Flughafen> flughafenAb = null;
	@FXML
	TableColumn<Angebot, Flughafen> flughafenAn = null;
	@FXML
	TableColumn<Angebot, String> abflugsZeit = null;
	@FXML
	TableColumn<Angebot, String> ankunftsZeit = null;
	
	DataManager db = DataManager.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.initTableView();
		this.fillAngebote();
		
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction(event -> {
		    Angebot item = tv.getSelectionModel().getSelectedItem();
		    db.deleteAngebot(item);
		    System.out.println("Selected item: " + item + " deleted");
		    this.fillAngebote();
		});

		ContextMenu menu = new ContextMenu();
		menu.getItems().add(delete);
		tv.setContextMenu(menu);
	}

	private void initTableView() {
		flugNummer = new TableColumn<Angebot, Integer>("Flug Nummer");
		flughafenAb = new TableColumn<Angebot, Flughafen>("Flughafen Ab.");
		flughafenAn = new TableColumn<Angebot, Flughafen>("Flughafen An.");
		abflugsZeit = new TableColumn<Angebot, String>("Abflug Zeit");
		ankunftsZeit = new TableColumn<Angebot, String>("Ankunft Zeit");

		flugNummer.setCellValueFactory(new PropertyValueFactory<Angebot, Integer>("flugNummer"));
		flughafenAb.setCellValueFactory(new PropertyValueFactory<Angebot, Flughafen>("flughafenAb"));
		flughafenAn.setCellValueFactory(new PropertyValueFactory<Angebot, Flughafen>("flughafenAn"));
		abflugsZeit.setCellValueFactory(new PropertyValueFactory<Angebot, String>("abflugsZeit"));
		ankunftsZeit.setCellValueFactory(new PropertyValueFactory<Angebot, String>("ankunftsZeit"));

		this.tv.getColumns().addAll(flugNummer, flughafenAb, flughafenAn, abflugsZeit, ankunftsZeit);

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


	private void fillAngebote() {
		try {
			angebote.clear();
			//angebote.addAll(db.getAngebote());
			ArrayList<Angebot> temp = new ArrayList<Angebot>();
			temp.add(new Angebot("123", null, new Flughafen(1, "Wien", "1234sdaf", "Wien"), new Flughafen(1, "Klagenfurt", "1234sdaf", "Klagenfurt"), "12:34", "15:15"));
			temp.add(new Angebot("157", null, new Flughafen(3, "Frankfurt", "1234sdaf", "Frankfurt"), new Flughafen(1, "Graz", "1234sdaf", "Graz"), "12:34", "15:15"));
			angebote.addAll(temp);

			this.tv.setItems(angebote);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void openUpdateDialog() {
		FXMLLoader loader = null;
		AnchorPane root = null;
		CreateAngebotController controller = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/gui/CreateAngebote.fxml"));
			root = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage currentStage = new Stage();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		currentStage.setTitle("Update Angebot");
		currentStage.setScene(scene);
		currentStage.initModality(Modality.APPLICATION_MODAL);
		controller.setAngebot(this.tv.getSelectionModel().getSelectedItem());
		currentStage.showAndWait();
		this.fillAngebote();

	}

	@FXML
	public void addAngebot() {
		FXMLLoader loader = null;
		AnchorPane root = null;
		CreateAngebotController controller = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/gui/CreateAngebote.fxml"));
			root = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage currentStage = new Stage();
		Scene scene = new Scene(root);
		currentStage.setTitle("Add Angebot");
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		currentStage.setScene(scene);
		currentStage.initModality(Modality.APPLICATION_MODAL);
		currentStage.showAndWait();
		this.fillAngebote();
	}

}
