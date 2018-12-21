package controllers;

import javafx.scene.control.Alert;
import launch.Main;
import models.Partie;
import models.Player;
import queries.Query;
import queries.QueryCheckLogin;
import views.VueLogin;

public class ControllerLogin {

    private VueLogin vueLogin;
    private Partie partie;

    public ControllerLogin(VueLogin vueLogin, Partie partie) {
        this.vueLogin = vueLogin;
        this.partie = partie;
    }

    public void connexion(VueLogin view) {
        partie.setLocal(false);
        partie.setDatabase();
        String nom = view.getUtilisateur().getText();
        String password = view.getMotdepasse().getText();
        String res = "0";
        String token;
        Query check = new QueryCheckLogin(partie.getDatabase(),nom,password);
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


}
