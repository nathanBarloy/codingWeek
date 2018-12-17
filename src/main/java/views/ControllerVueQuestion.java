
package views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import models.*;

import javafx.fxml.FXML;


import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ControllerVueQuestion implements Observer {
    private Partie partie;

    @FXML
    private BorderPane pane;

    @FXML
    private Label LabelNom;

    @FXML
    private Label LabelQuestion;

    public ControllerVueQuestion(Partie partie) {
        super();
        this.partie = partie;
        this.partie.addObserver(this);
    }

    public void NvQuest() {
        this.partie.NvQuest();
    }

    public void valider() {
        this.partie.valider();
    }

    @Override
    public void update(Observable o, Object arg) {
        Image image1 = new Image("https://ma-credence-deco.com/2349-thickbox_default/lotus-et-lumiere-fond-blanc.jpg");
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

        Background background2 = new Background(new BackgroundImage(image1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize));

        this.pane.setBackground(new Background(new BackgroundImage(image1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
        Card carte = partie.getCurrentCard();
        //System.out.println(carte.getQuestion());
        if (carte.getType().equals("question")) {
            //System.out.println("question");
            this.LabelQuestion.setText(carte.getQuestion());
        }
        if (carte.getType().equals("reponse")) {
            //System.out.println("reponse");
            this.LabelQuestion.setText(carte.getAnswer());
        }
    }

}
