package views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Observable;
import java.util.Observer;

public class ControllerCard implements Observer {
    @FXML
    private Label LabelQuestion;

    public ControllerCard() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
