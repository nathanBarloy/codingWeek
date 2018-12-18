package views;

import launch.Main;

public class VueLogin {

    public void connexion() {
        Main.main.switchScene("/views/VueMenu.fxml");
    }

    public void quitter() {
        Main.main.closeStage();
    }

    public void inscription() {
        Main.main.switchScene("/views/VueInscription.fxml");
    }

}
