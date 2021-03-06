package views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import launch.Main;
import models.Partie;
import models.Player;
import queries.Query;
import queries.QueryAddUser;
import queries.QueryCheckUsername;


import java.util.Observable;
import java.util.Observer;

public class VueInscription implements Observer{
    private Partie partie;

    @FXML
    private BorderPane borderpane;

    @FXML
    private TextField utilisateur;

    @FXML
    private PasswordField motdepasse;

    @FXML
    private PasswordField confirmation;


    public VueInscription(Partie partie) {
        super();
        this.partie = partie;
        partie.addObserver(this);
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
            Query check = new QueryCheckUsername(partie.getDatabase(),nom);
            String resp = "error";

                check.send();
                resp = check.getResponse(); //on regarde si le nom entré existe déjà
                System.out.println(resp);

            if (resp.equals("0")) { //si le nom n'existe pas (cas correct)
                Player player = new Player(nom);

                Query query = new QueryAddUser(partie.getDatabase(),nom,mdp);
                query.send();
                    resp = query.getResponse();
                System.out.println(resp);

                retour();

            } else { //si le nom existe (erreur)
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Ce nom a déjà été pris");
                String message = "";

                alert.setContentText(message);
                alert.showAndWait();
            }

        } else { //si les informations entrées ne sont pas correctes
            if (nom.length()<3) { //erreur nom pas assez long
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Nom pas assez long");
                String message = "il faut au moins 3 caractères";

                alert.setContentText(message);
                alert.showAndWait();
            } else if (mdp.length()<6) { // erreur mdp pas assez long
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Mot de passe pas assez long");
                String message = "il faut au moins 6 caractères";

                alert.setContentText(message);
                alert.showAndWait();
            } else if (!mdp.equals(confirm)) { // erreur mdp non confirmé
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Mauvaise confirmation du mot de passe");
                String message = "confirmez votre mot de passe";

                alert.setContentText(message);
                alert.showAndWait();
            }



        }

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

    }
}
