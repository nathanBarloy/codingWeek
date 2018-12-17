
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

    @FXML
    private Label LabelQuestion;

    public ControllerVueQuestion(Partie partie) {
        super();
        this.partie = partie;
        this.partie.addObserver(this);
    }

    public void NvQuest(){
        this.partie.NvQuest();
    }
    public void valider(){
        this.partie.valider();
    }
    @Override
    public void update(Observable o, Object arg) {
        Card carte = partie.getCurrentCard();
        if (carte.getType().equals("question"))
        this.LabelQuestion.setText(carte.getQuestion());
        if (carte.getType().equals("reponse"))
            this.LabelQuestion.setText(carte.getAnswer());

    }
}
