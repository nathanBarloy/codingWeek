package views;

import controllers.ControllerMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import java.util.Optional;

public class VueMenu implements Observer{
    Partie partie;
    boolean init;
    ControllerMenu controllerMenu;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Label label;

    @FXML
    private Button boutonSuppression;

    public VueMenu(Partie p){
        super();
        init = true;
        this.partie = p;
        this.partie.addObserver(this);
        this.controllerMenu=new ControllerMenu(this,this.partie);
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
        controllerMenu.quitter();
    }

    public void deconnexion() {
        controllerMenu.deconnexion();
    }

    public void supprimerCompte() {
        controllerMenu.supprimerCompte();

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

            if (partie.isLocal()) {
                boutonSuppression.setDisable(true);
                boutonSuppression.setVisible(false);
            }

            init = false;
        } else {

        }
    }
}
