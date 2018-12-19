package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import launch.Main;
import models.Partie;
import models.Player;
import queries.Query;
import queries.QueryCheckUsername;


import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.Key;
import java.util.Observable;
import java.util.Observer;

public class VueLogin implements Observer {

    private Partie partie;

    @FXML
    private Pane pane;

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

        Image image0 = new Image("/resources/img/flashcards.png");

        //final URL imageURL = getClass().getResource("../ressources/fond");
        //final Image image1 = new Image(imageURL.toExternalForm());

        ImageView img = new ImageView();
        img.setImage(image0);
        img.setFitHeight(275);
        img.setFitWidth(700);
        img.setLayoutX(200);

        this.pane.getChildren().add(img);
    }
}
