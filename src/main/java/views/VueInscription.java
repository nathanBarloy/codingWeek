package views;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import launch.Main;
import models.Partie;


import java.util.Observable;
import java.util.Observer;

public class VueInscription implements Observer{
    private Partie p;


    @FXML
    private TextField utilisateur;

    @FXML
    private PasswordField motdepasse;

    @FXML
    private PasswordField confirmation;


    public VueInscription(Partie p) {
        super();
        this.p = p;
        p.addObserver(this);
    }


    public void retour() {
        Main.main.switchScene("/views/VueLogin.fxml");
    }

    public void validerInscription() {
        String nom = utilisateur.getText();
        String mdp = motdepasse.getText();
        String confirm = confirmation.getText();

        if ( !(nom.length()<3 || mdp.length()<6 || !mdp.equals(confirm)) ) { //si informations sont correctes
            //ajouter utilisateur à la BDD
            

            retour();
        } else {

        }

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
