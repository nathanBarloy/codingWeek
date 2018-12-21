package controllers;

import launch.Main;
import models.Partie;
import queries.Query;
import queries.QueryDelUser;
import views.VueMenu;

import java.util.Optional;

public class ControllerMenu {

    private VueMenu vueMenu;
    private Partie partie;

    public ControllerMenu(VueMenu vueMenu, Partie partie) {
        this.vueMenu = vueMenu;
        this.partie = partie;
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
}
