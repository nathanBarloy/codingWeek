package controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import launch.Main;
import models.Partie;
import queries.Query;
import queries.QueryDelUser;
import queries.QueryLogout;
import views.VueMenu;

import java.util.Optional;

public class ControllerMenu {


    private Partie partie;



    public ControllerMenu(Partie partie){
        this.partie = partie;
    }

    public void quitter() {
        deconnexion();
        Main.main.closeStage();
    }

    public void deconnexionSansConnexion() {
        this.partie.setLocal(true);
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


    public void deconnexion(){
            Query query= new QueryLogout(this.partie.getDatabase());
            query.send();
            deconnexionSansConnexion();
    }
}
