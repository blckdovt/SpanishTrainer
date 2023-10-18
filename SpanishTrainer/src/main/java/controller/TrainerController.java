package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Vokabel;

public class TrainerController implements Initializable{

	@FXML
	private Button irButton;
	@FXML
	private Button borrarButton;
	@FXML
	private Button empezarButton;
	@FXML
	private Button addButton;
	@FXML
	private Button resolverButton;
	@FXML
	private TextField textField = new TextField();
	@FXML
	private Label displayLabel = new Label();
	@FXML
	private Label intentosLabel;
	@FXML
	private Label puntajeLabel;
	@FXML
	private Label numeroPuntaje;
	@FXML
	private Label numeroIntentos;
	@FXML
	private Button specialChar1;
	@FXML
	private Button specialChar2;
	@FXML
	private Button specialChar3;
	@FXML
	private Button specialChar4;
	@FXML
	private Button specialChar5;
	@FXML
	private Button specialChar6;
	@FXML
	private Button specialChar7;
	@FXML
	private Button specialChar8;
	@FXML
	private Button specialChar9;
	@FXML
	private Button specialChar10;
	@FXML
	private Button specialChar11;
	@FXML
	private Button specialChar12;
	@FXML
	private Button specialChar13;
	@FXML
	private Button specialChar14;
	@FXML
	private Button specialChar15;
	@FXML
	private Button specialChar16;
	@FXML
	private Button specialChar17;
	@FXML
	private Button specialChar18;

	private static Configuration config;

	private static SessionFactory sf;

	private static List<Vokabel> vokabelListe;

	private Vokabel vokabel;

	private Random random  = new Random();

	private int languageChooser;

	private int intentos = 0;
	private int puntaje = 0;

	// statischer Initialisierer: um nur einmal die Configuration aufzurufen und die SessionFactory
	// zu bilden
	static {
		config = new Configuration().configure().addAnnotatedClass(Vokabel.class);
		setSf(config.buildSessionFactory());
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		textField.setDisable(true);
		irButton.setDisable(true);
		borrarButton.setDisable(true);
	}

	// Kostruktor holt alle Vokabel aus DB und speichert sie lokal in Liste ab
	public TrainerController() {
		Session session = getSf().openSession();
		Transaction txn = session.beginTransaction();

		Query<Vokabel> query = session.createQuery("FROM Vokabel");

		vokabelListe = query.list();

		txn.commit();
		session.close();
	}

	// EMPEZAR-BUTTON: Starte das Spiel (auf Label wird Vokabel angezeigt - nur am Anfang)
	@FXML
	private void startGame() {
		textField.setDisable(false);

		irButton.setDisable(false);
		borrarButton.setDisable(false);
		empezarButton.setDisable(true);

		numeroPuntaje.setText(String.valueOf(puntaje));
		numeroIntentos.setText(String.valueOf(intentos));

		nextWord();
	}

	// IR-BUTTON: Vergleicht Input in Textfeld und überprüft Korrektheit
	@FXML
	private void acceptInput () throws InterruptedException {
		intentos++;
		numeroIntentos.setText(String.valueOf(intentos));

		if(displayLabel.getText().equals(vokabel.getSpanish())) {
			if(textField.getText().equals(vokabel.getGerman())) {
				puntaje++;
				numeroPuntaje.setText(String.valueOf(puntaje));
				displayLabel.setTextFill(Color.GREEN);
				textField.clear();
				nextWord();
			} else {
				displayLabel.setTextFill(Color.RED);
			}
		}
		else {
			if(textField.getText().equals(vokabel.getSpanish())) {
				puntaje++;
				numeroPuntaje.setText(String.valueOf(puntaje));
				displayLabel.setTextFill(Color.GREEN);
				textField.clear();
				nextWord();
			} else {
				displayLabel.setTextFill(Color.RED);
			}
		}
	}

	@FXML
	private void deleteInput() {
		textField.clear();
	}

	// Eigentliche Spielmechanik, um bei richtigem Tipp auf nächstes Vokabel zu switchen
	@FXML
	private void nextWord() {
		displayLabel.setTextFill(Color.BLACK);

		vokabel = vokabelListe.get(random.nextInt(vokabelListe.size()));
		languageChooser = random.nextInt(2);

		if(languageChooser == 1) {
			displayLabel.setText(vokabel.getSpanish());
		} else {
			displayLabel.setText(vokabel.getGerman());
		}
	}

	// Um richtiges Vokabel bzw. die Lösung anzuzeigen
	@FXML
	void solve() {

	}

	// Öffnet Fenster, um neue Vokabel zur Datenbank hinzuzufügen
	@FXML
	private void addVocabulary() throws IOException {
		//FXML File laden und neues Pop-Up erzeugen
		FXMLLoader fxmlloader = new FXMLLoader();
		fxmlloader.setLocation(getClass().getResource("/gui/Add_vocabulary.fxml"));
		DialogPane dialogPane = fxmlloader.load();

		//Controller laden
		AddVocabularyController addController = new AddVocabularyController();

		//Dialog Fenster aufbauen
		Dialog<ButtonType> dialog = new Dialog();
		dialog.setDialogPane(dialogPane);
		dialog.setTitle("Añadir vocabulario");

		dialogPane.getButtonTypes().add(ButtonType.CLOSE);

		dialog.showAndWait();
	}

	///////////////////////////////// SPECIAL CHARS BUTTONS //////////////////////////////////

	@FXML
	void specialChar1pressed() {

	}

	@FXML
	void specialChar2pressed() {

	}

	@FXML
	void specialChar3pressed() {

	}

	@FXML
	void specialChar4pressed() {

	}

	@FXML
	void specialChar5pressed() {

	}

	@FXML
	void specialChar6pressed() {

	}

	@FXML
	void specialChar7pressed() {

	}

	@FXML
	void specialChar8pressed() {

	}

	@FXML
	void specialChar9pressed() {

	}

	@FXML
	void specialChar10pressed() {

	}

	@FXML
	void specialChar11pressed() {

	}

	@FXML
	void specialChar12pressed() {

	}

	@FXML
	void specialChar13pressed() {

	}

	@FXML
	void specialChar14pressed() {

	}

	@FXML
	void specialChar15pressed() {

	}

	@FXML
	void specialChar16pressed() {

	}

	@FXML
	void specialChar17pressed() {

	}

	@FXML
	void specialChar18pressed() {

	}
	
	///////////////////////////////// GETTERS & SETTERS //////////////////////////////////////

	public static List<Vokabel> getVokabelListe() {
		return vokabelListe;
	}

	public static void setVokabelListe(List<Vokabel> vokabelListe) {
		TrainerController.vokabelListe = vokabelListe;
	}

	public static SessionFactory getSf() {
		return sf;
	}

	public static void setSf(SessionFactory sf) {
		TrainerController.sf = sf;
	}

}
