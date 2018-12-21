package views;

import controllers.ControllerLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import launch.Main;
import models.Partie;
import models.Player;
import queries.*;

import javax.swing.plaf.basic.BasicButtonUI;
import java.util.Observable;
import java.util.Observer;

public class VueLogin implements Observer {

    private Partie partie;

    @FXML
    private Button buttonConnexion;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Pane pane;

    @FXML
    private TextField utilisateur;

    @FXML
    private PasswordField motdepasse;

    private ControllerLogin controllerLogin;

    public VueLogin(Partie p){
        super();
        this.partie = p;
        this.partie.addObserver(this);
        this.controllerLogin = new ControllerLogin( partie);
    }


    public TextField getUtilisateur() {
        return utilisateur;
    }

    public PasswordField getMotdepasse() {
        return motdepasse;
    }

    public void quitter() {
        Main.main.closeStage();
    }

    public void inscription() {
        Main.main.switchScene("/views/VueInscription.fxml");
    }

    @Override
    public void update(Observable o, Object arg) {

        Image image4 = new Image("/resources/img/connexion.png");
        ImageView img4 = new ImageView(image4);
        this.buttonConnexion.setGraphic(img4);
        img4.setFitHeight(50);
        img4.setFitWidth(150);

        Image image1 = new Image("/resources/img/lotus.jpg");
        BackgroundSize bSize0 = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);

        Background background1 = new Background(new BackgroundImage(image1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bSize0));

        this.borderpane.setBackground(new Background(new BackgroundImage(image1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bSize0)));

        Image image0 = new Image("/resources/img/flashcards.png");

        //final URL imageURL = getClass().getResource("../ressources/fond");
        //final Image image1 = new Image(imageURL.toExternalForm());

        ImageView img = new ImageView();
        img.setImage(image0);
        img.setFitHeight(220);
        img.setFitWidth(500);
        img.setLayoutY(-30);
        img.setLayoutX(150);

        this.pane.getChildren().add(img);


    }



    public void connexion() {

        controllerLogin.connexion(this);


    }

    public void connexionLocal() {

        controllerLogin.connexionLocal();
    }
}
