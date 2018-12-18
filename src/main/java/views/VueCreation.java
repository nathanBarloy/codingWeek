package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import launch.Main;
import models.Partie;

import java.util.Observable;
import java.util.Observer;

public class VueCreation implements Observer {

    private Partie partie;
    String nomCarte,questionCarte,reponseCarte,nomDeck;
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
    }

    public void retour() {
        Main.main.switchScene("/views/VueMenu.fxml");
    }

    public void valider() {
        questionCarte = question.getText();
        reponseCarte = reponse.getText();
        nomCarte = nom.getText();
        nomDeck = (String) menuDeck.getValue();

        //System.out.println(questionCarte + "\n" + reponseCarte + "\n" + nomCarte);
        if ( !(questionCarte.equals("") || reponseCarte.equals("") || nomCarte.equals("") || nomDeck==null) ) {
            question.setText("");
            reponse.setText("");
            nom.setText("");

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR");
            alert.setHeaderText("Vous ne pouvez pas encore valider");
            String message = "";
            if (questionCarte.equals("")) {
                message += "La carte n'a pas de question\n";
            }
            if (reponseCarte.equals("")) {
                message += "La carte n'a pas de réponse\n";
            }
            if (nomCarte.equals("")) {
                message+= "La carte n'a pas de nom\n";
            }
            if (nomDeck==null) {
                message += "aucun deck n'a été choisi\n";
            }

            alert.setContentText(message);

            alert.showAndWait();

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (init) {

            menuDeck.setItems(FXCollections.observableArrayList(

                    "deck 1",
                    "deck 2"
            ));
        } else {

        }
    }
}
