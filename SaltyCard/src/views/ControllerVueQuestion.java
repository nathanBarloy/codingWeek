package views;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import models.Partie;
import sample.Main;

import java.util.Observable;
import java.util.Observer;

public class ControllerVueQuestion implements Observer{
    private Main m;
    private Partie p;

    @FXML
    private BorderPane borderpane;

    public ControllerVueQuestion(Partie partie){
        super();
        this.p = partie;
        this.p.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
