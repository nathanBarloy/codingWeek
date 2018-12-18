package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import launch.Main;
import models.Partie;

import java.util.Observable;
import java.util.Observer;

public class VueCreation implements Observer {

    private Partie partie;
    String nomCarte,questionCarte,reponseCarte;
    boolean init;

    @FXML
    private TextArea question;

    @FXML
    private TextArea reponse;

    @FXML
    private TextField nom;

    @FXML
    private ChoiceBox menuDeck;

    public VueCreation(Partie partie) {
        super();
        init = true;
        this.partie = partie;
        this.partie.addObserver(this);
        partie.init();
    }

    public void retour() {
        Main.main.switchScene("/views/VueMenu.fxml");
    }

    public void valider() {
        questionCarte = question.getText();
        reponseCarte = reponse.getText();
        nomCarte = nom.getText();
        //System.out.println(questionCarte + "\n" + reponseCarte + "\n" + nomCarte);
        if ( !(questionCarte.equals("") || reponseCarte.equals("") || nomCarte.equals("")) ) {
            question.setText("");
            reponse.setText("");
            nom.setText("");

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (init) {
            System.out.println(menuDeck);

            menuDeck.setItems(FXCollections.observableArrayList(
                    "deck 1",
                    "deck 2"
            ));
        } else {

        }
    }
}
