package views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import launch.Main;
import models.Card;
import models.Partie;
import models.Player;

import java.util.Observable;
import java.util.Observer;

public class VueCard implements Observer {

    @FXML
    private Button buttonValider;
    @FXML
    private Button buttonRetour;
    @FXML
    private Label LabelQuestion;
    @FXML
    private Label LabelReponse;
    @FXML
    private Label LabelNom;


    private Partie partie;
    private String NomDeck;

    public VueCard(Partie p){
        super();
        this.partie = p;
        this.partie.addObserver(this);
    }



    public void Valider(){
                this.partie.addCard(NomDeck, new Card(this.partie.getNameEnCours(), this.partie.getQuestEnCours(),
                this.partie.getRepEnCours(), this.partie.getPlayer()));
        Main.main.switchScene("/views/VueCreation.fxml", this.partie);
    }

    public void Retour(){
        Main.main.switchScene("/views/VueCreation.fxml", this.partie);
    }



    @Override
    public void update(Observable o, Object arg) {
        Image image2 = new Image("/resources/img/retour.png");
        ImageView img = new ImageView(image2);
        img.setFitHeight(75);
        img.setFitWidth(125);
        this.buttonRetour.setGraphic(img);

        Image image3 = new Image("/resources/img/valider.png");
        ImageView img3 = new ImageView(image3);
        img3.setFitHeight(50);
        img3.setFitWidth(60);
        this.buttonValider.setGraphic(img3);


        this.LabelQuestion.setText(this.partie.getQuestEnCours());
        this.LabelReponse.setText(this.partie.getRepEnCours());
        this.LabelNom.setText(this.partie.getNameEnCours());
        this.NomDeck = this.partie.getcurrentDeck();
    }
}
