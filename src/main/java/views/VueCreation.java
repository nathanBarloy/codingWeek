package views;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import launch.Main;
import models.Partie;

import java.util.Observable;
import java.util.Observer;

public class VueCreation implements Observer {

    private Partie partie;
    String nomCarte,questionCarte,reponseCarte;

    @FXML
    private TextArea question;

    @FXML
    private TextArea reponse;

    @FXML
    private TextField nom;

    public VueCreation(Partie partie) {
        super();
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
        //System.out.println(questionCarte + "\n" + reponseCarte + "\n" + nomCarte);
        if ( !(questionCarte.equals("") || reponseCarte.equals("") || nomCarte.equals("")) ) {
            question.setText("");
            reponse.setText("");
            nom.setText("");

        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
