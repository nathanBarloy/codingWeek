package views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Partie;

import java.util.Observable;
import java.util.Observer;

public class VueCard implements Observer {
    @FXML
    private Label LabelQuestion;
    @FXML
    private Label LabelReponse;


    private Partie partie;

    public VueCard(Partie p){
        super();
        this.partie = p;
        this.partie.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        this.LabelQuestion.setText(this.partie.getQuestEnCours());
        this.LabelReponse.setText(this.partie.getRepEnCours());
    }
}
