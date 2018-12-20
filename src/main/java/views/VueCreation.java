package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import launch.Main;
import models.Partie;
import queries.Query;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VueCreation implements Observer {

    private Partie partie;
    String nomCarte,questionCarte,reponseCarte,nomDeck;
    boolean init;

    @FXML
    private TextField newdeck;

    @FXML
    private TextArea question;

    @FXML
    private TextArea reponse;

    @FXML
    private TextField nom;

    @FXML
    private ComboBox menuDeck;

    public VueCreation(Partie partie) {
        super();
        init = true;
        this.partie = partie;
        this.partie.addObserver(this);
    }

    public void AjouterDeck(){
        //System.out.println("AjouterDeck");
        this.partie.addDeck(this.newdeck.getText());

        this.newdeck.setText("");
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
            this.partie.setQuestEnCours(questionCarte);
            this.partie.setRepEnCours(reponseCarte);
            this.partie.setNameEnCours(nomCarte);
            this.partie.setDeckEnCours((String)this.menuDeck.getValue());
            //System.out.println("quest:" + partie.getQuestEnCours());
            Main.main.switchScene("/views/VueCard.fxml",this.partie);

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
                message += "Aucun deck n'a été choisi\n";
            }

            alert.setContentText(message);

            alert.showAndWait();

        }
    }

    public void supprimerCarte() {

    }

    @Override
    public void update(Observable o, Object arg) {

            ArrayList<String> deckList = partie.getListeDeck();
            menuDeck.setItems(FXCollections.observableArrayList( deckList ));
    }
}
