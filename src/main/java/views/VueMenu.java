package views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    private Button buttonQuitter;

    @FXML
    private Button buttonSupprimer;

    @FXML
    private Button buttonDeconnexion;

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
        this.partie.exportDatabaseLocal();
        this.partie.setDatabase();
        Main.main.switchScene("/views/VueLogin.fxml");
    }

    public void supprimerCompte() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer Suppression");
        alert.setHeaderText("Attention, vous allez supprimer votre compte");
        alert.setContentText("Cette action sera irréversible");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            Query del = new QueryDelUser(partie.getDatabase(),partie.getPlayer());
            
            del.send();
            if(del.getResponse().equals("1")) {
                System.out.println("User supprimé");
                deconnexion();
            }else{
                System.out.println("Erreur : "+del.getResponse());
            }

        } else {
            // ... user chose CANCEL or closed the dialog
        }


    }

    @Override
    public void update(Observable o, Object arg) {
        if (init) {
            Image image2 = new Image("/resources/img/quitter.jpg");
            ImageView img2 = new ImageView(image2);
            this.buttonQuitter.setGraphic(img2);
            img2.setFitHeight(50);
            img2.setFitWidth(150);

            Image image3 = new Image("/resources/img/supprimer.jpg");
            ImageView img3 = new ImageView(image3);
            this.buttonSupprimer.setGraphic(img3);
            img3.setFitHeight(50);
            img3.setFitWidth(50);

            Image image4 = new Image("/resources/img/connexion.jpg");
            ImageView img4 = new ImageView(image4);
            this.buttonDeconnexion.setGraphic(img4);
            img4.setFitHeight(50);
            img4.setFitWidth(150);


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
