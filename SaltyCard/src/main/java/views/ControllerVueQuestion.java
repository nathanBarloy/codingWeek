
package SaltyCard.src.main.java.views;
import SaltyCard.src.main.java.models.*;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;


import java.util.Observable;
import java.util.Observer;

public class ControllerVueQuestion implements Observer{
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
