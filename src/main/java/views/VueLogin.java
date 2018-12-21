package views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import launch.Main;
import models.Partie;
import models.Player;
import queries.*;

import java.util.Observable;
import java.util.Observer;

public class VueLogin implements Observer {

    private Partie partie;

    @FXML
    private BorderPane borderpane;

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
        partie.setLocal(false);
        partie.setDatabase();
        String nom = utilisateur.getText();
        String password = motdepasse.getText();
        String res = "0";
        String token;
        Query check = new QueryCheckLogin(nom,password);
        check.send();
        res = check.getResponse();
        token=check.getToken();
        System.out.println(token);
        System.out.println(res);

        if (res.equals("-2") ) { //si le nom entré est dans la BDD
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("L'utilisateur n'existe pas");
            String message = "";

            alert.setContentText(message);
            alert.showAndWait();
            //System.out.println("L'utilisateur n'existe pas");

        }else if(res.equals("-3")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Password erroné");
            String message = "";

            alert.setContentText(message);
            alert.showAndWait();
            System.out.println("Password erroné");

        }else {
            partie.setPlayer(new Player(nom));
            partie.setSessionToken(token);
            Main.main.switchScene("/views/VueMenu.fxml");

        }



    }

    public void connexionLocal() {

        partie.setPlayer(new Player(""  ));
        System.out.println("preimport");
        partie.setLocal(true);
        partie.setDatabase();
        System.out.println("postimport");
        Main.main.switchScene("/views/VueMenu.fxml");
    }


    public void quitter() {
        Main.main.closeStage();
    }

    public void inscription() {
        Main.main.switchScene("/views/VueInscription.fxml");
    }

    @Override
    public void update(Observable o, Object arg) {


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
}
