package views;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import launch.Main;
import models.Partie;
import models.Player;
import queries.Query;
import queries.QueryCheckUsername;


import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class VueLogin implements Observer {

    private Partie partie;

    @FXML
    private TextField utilisateur;

    @FXML
    private PasswordField motdepasse;

    public VueLogin(Partie p){
        super();
        this.partie = p;
        this.partie.addObserver(this);
    }

    public void connexion() {
        String nom = utilisateur.getText(), res = "0";
        Query check = new QueryCheckUsername(nom);
        try {
            check.send();
            res = check.getResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (res.equals("1")) { //si le nom entré est dans la BDD
            partie.setPlayer(new Player(nom));
            Main.main.switchScene("/views/VueMenu.fxml");
        } else {
            Main.main.switchScene("/views/VueMenu.fxml");
        }


    }

    public void quitter() {
        Main.main.closeStage();
    }

    public void inscription() {
        Main.main.switchScene("/views/VueInscription.fxml");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
