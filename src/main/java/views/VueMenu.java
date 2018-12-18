package views;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import launch.Main;
import models.Partie;

import java.util.Observable;
import java.util.Observer;

public class VueMenu implements Observer{
    Partie partie;
    @FXML
    private BorderPane borderpane;

    public VueMenu(Partie p){
        super();
        this.partie = p;
        this.partie.addObserver(this);
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

    @Override
    public void update(Observable o, Object arg) {
        Image image0 = new Image("https://ma-credence-deco.com/2349-thickbox_default/lotus-et-lumiere-fond-blanc.jpg");

        //final URL imageURL = getClass().getResource("../ressources/fond");
        //final Image image1 = new Image(imageURL.toExternalForm());


        BackgroundSize bSize0 = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

        Background background1 = new Background(new BackgroundImage(image0,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize0));

        this.borderpane.setBackground(new Background(new BackgroundImage(image0,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize0)));
    }
}
