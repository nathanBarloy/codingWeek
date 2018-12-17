
package views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import models.*;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;


import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ControllerVueQuestion implements Observer{
    private Partie partie;

    @FXML
    private BorderPane pane;

    @FXML
    private Label LabelNom;

    public ControllerVueQuestion(Partie partie) {
        super();
        this.partie = partie;
        this.partie.addObserver(this);
    }

    public void InitPremiereCard(){
        this.partie.init();
    }
    public void valider(){
        this.partie.valider();
    }
    @Override
    public void update(Observable o, Object arg) {
        Card carte = new Card("une question","quelle est la 101 décimale de pi?","1");
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("VueCard.fxml"));
        loader2.setControllerFactory(iC->new ControllerCard(this.partie));
        Parent VueQuestion = null;
        try {
            VueQuestion = loader2.load();
            this.pane.setCenter(VueQuestion);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
