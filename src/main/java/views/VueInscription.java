package views;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import launch.Main;
import models.Partie;
import models.Player;
import queries.Query;
import queries.QueryAddUser;
import queries.QueryCheckUsername;


import java.io.IOException;
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
            Query check = new QueryCheckUsername(nom);
            String resp = "error";

                check.send();
                resp = check.getResponse(); //on regarde si le nom entré existe déjà
                System.out.println(resp);

            if (resp.equals("0")) { //si le nom n'existe pas (cas correct)
                Player player = new Player(nom);

                Query query = new QueryAddUser(nom,mdp);
                query.send();


                retour();

            } else { //si le nom existe (erreur)
                System.out.println("Le nom existe déjà");
            }

        } else { //si les informations entrées ne sont pas correctes

        }

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
