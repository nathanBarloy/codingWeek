package views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import launch.Main;
import models.Partie;
import queries.Query;
import queries.QueryDelUser;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class VueMenu implements Observer{
    Partie partie;
    boolean init;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Label label;

    public VueMenu(Partie p){
        super();
        init = true;
        this.partie = p;
        this.partie.addObserver(this);
    }

    public void evaluation(){
        Main.main.switchScene("/views/VueEvalQuestion.fxml",this.partie);
    }

    public void ConsulterDecks(){
        Main.main.switchScene("/views/Decks.fxml",this.partie);
    }
    public void lancerModeTest() {
        Main.main.switchScene("/views/VueQuestion.fxml");
    }

    public void lancerModeCreation() {
        Main.main.switchScene("/views/VueCreation.fxml");
    }

    public void quitter() {
        Main.main.closeStage();
    }

    public void deconnexion() {
        Main.main.switchScene("/views/VueLogin.fxml");
    }

    public void supprimerCompte() {
        Query del = new QueryDelUser(partie.getPlayer());
        try {
            del.send();
            deconnexion();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (init) {
            Image image0 = new Image("/resources/img/lotus.jpg");

            //final URL imageURL = getClass().getResource("../ressources/fond");
            //final Image image1 = new Image(imageURL.toExternalForm());


            BackgroundSize bSize0 = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);

            Background background1 = new Background(new BackgroundImage(image0,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    bSize0));

            this.borderpane.setBackground(new Background(new BackgroundImage(image0,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    bSize0)));

            //System.out.println(label);
            label.setText("Bienvenue " + partie.getPlayer().getUsername());
            init = false;
        } else {

        }
    }
}
