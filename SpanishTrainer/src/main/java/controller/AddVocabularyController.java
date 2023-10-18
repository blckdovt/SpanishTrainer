package controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Vokabel;

public class AddVocabularyController implements Initializable{

	@FXML
	private Button guardarButton;
	@FXML
	private Button borrarButton;
	@FXML
	private TextField textoEspanol;
	@FXML
	private TextField textoAleman;
	@FXML
    private Label errorLabelAleman;
    @FXML
    private Label errorLabelEspanol;
	@FXML
	private TableView<Vokabel> vocabularyTable = new TableView();
	@FXML
	private TableColumn<Vokabel, String> alemanColumn;
	@FXML
	private TableColumn<Vokabel, String> espanolColumn;

	ObservableList<Vokabel> voklist;
	
	// Tabelle aufbauen
	public void initialize(URL arg0, ResourceBundle arg1) {
		alemanColumn.setCellValueFactory(new PropertyValueFactory<Vokabel, String>("german"));
		espanolColumn.setCellValueFactory(new PropertyValueFactory<Vokabel, String>("spanish"));
		voklist = FXCollections.observableArrayList(TrainerController.getVokabelListe());
		vocabularyTable.setItems(voklist);
		
		errorLabelAleman.setVisible(false);
		errorLabelEspanol.setVisible(false);
	}
	
	// Vokabel zu DB hinzufügen + Abfrage auf leere Textfelder
	@FXML
	private void saveVocabulary() {			
		errorLabelAleman.setVisible(false);
		errorLabelEspanol.setVisible(false);
		
		if(textoAleman.getText().isEmpty()) {
			errorLabelAleman.setVisible(true);
		}
		if (textoEspanol.getText().isEmpty()) {
			errorLabelEspanol.setVisible(true);
		}	
		if (!textoAleman.getText().isEmpty() && !textoEspanol.getText().isEmpty()) {
			Session session = TrainerController.getSf().openSession();
			Transaction txn = session.beginTransaction();
			
			Vokabel vokabel = new Vokabel();
			vokabel.setGerman(textoAleman.getText());
			vokabel.setSpanish(textoEspanol.getText());
			
			session.save(vokabel);
			TrainerController.getVokabelListe().add(vokabel);
			
			txn.commit();
			session.close();
			
			refreshTable();
		}
	}

	// Vokabel aus DB löschen
	@FXML
	private void deleteVocabulary() {
		Session session = TrainerController.getSf().openSession();
		Transaction txn = session.beginTransaction();

		session.delete(vocabularyTable.getSelectionModel().getSelectedItem());
		TrainerController.getVokabelListe().remove(vocabularyTable.getSelectionModel().getSelectedItem());

		txn.commit();
		session.close();

		refreshTable();
	}

	// einfache, eigene Refresh-Methode, da tableView.refresh() wieder mal nicht funkt...
	private void refreshTable() {
		voklist = FXCollections.observableArrayList(TrainerController.getVokabelListe());
		vocabularyTable.setItems(voklist);
	}

}
