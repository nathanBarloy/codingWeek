package views;
import models.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Observable;
import java.util.Observer;

public class ControllerCard implements Observer {
    private Partie partie;
    @FXML
    private Label LabelQuestion;

    public ControllerCard(Partie p) {
        super();
        this.partie = p;
        this.partie.addObserver(this);


    }

    @Override
    public void update(Observable o, Object arg) {
        this.LabelQuestion.setText("lol");
    }
}
