package SaltyCard.src.main.java.views;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import SaltyCard.src.main.java.models.Partie;
import SaltyCard.src.main.java.Main;

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
