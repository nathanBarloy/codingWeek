package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import launch.Main;
import models.Partie;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VueCreation implements Observer {

    private Partie partie;
    String nomCarte,questionCarte,reponseCarte,nomDeck;
    boolean init;


    @FXML
    private Button buttonValider;

    @FXML
    private Button buttonRetour;

    @FXML
    private BorderPane borderpane;

    @FXML
    private TextField newdeck;

    @FXML
    private TextArea question;

    @FXML
    private TextArea reponse;

    @FXML
    private TextField nom;

    @FXML
    private ComboBox menuDeck;

    public VueCreation(Partie partie) {
        super();
        init = true;
        this.partie = partie;
        this.partie.addObserver(this);
    }

    public void AjouterDeck(){
        //System.out.println("AjouterDeck");

        String newnom = this.newdeck.getText();

        if (!newnom.equals("")) {
            this.partie.addDeck(newnom);

            this.newdeck.setText("");
        }
    }

    public void retour() {
        Main.main.switchScene("/views/VueMenu.fxml");
    }

    public void valider() {
        questionCarte = question.getText();
        reponseCarte = reponse.getText();
        nomCarte = nom.getText();
        nomDeck = (String) menuDeck.getValue();

        //System.out.println(questionCarte + "\n" + reponseCarte + "\n" + nomCarte);
        if ( !(questionCarte.equals("") || reponseCarte.equals("") || nomCarte.equals("") || nomDeck==null) ) {

            question.setText("");
            reponse.setText("");
            nom.setText("");
            this.partie.setQuestEnCours(questionCarte);
            this.partie.setRepEnCours(reponseCarte);
            this.partie.setNameEnCours(nomCarte);
            this.partie.setcurrentDeck((String)this.menuDeck.getValue());
            //System.out.println("quest:" + partie.getQuestEnCours());
            Main.main.switchScene("/views/VueCard.fxml",this.partie);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR");
            alert.setHeaderText("Vous ne pouvez pas encore valider");
            String message = "";
            if (questionCarte.equals("")) {
                message += "La carte n'a pas de question\n";
            }
            if (reponseCarte.equals("")) {
                message += "La carte n'a pas de réponse\n";
            }
            if (nomCarte.equals("")) {
                message+= "La carte n'a pas de nom\n";
            }
            if (nomDeck==null) {
                message += "Aucun deck n'a été choisi\n";
            }

            alert.setContentText(message);

            alert.showAndWait();

        }
    }

    public void supprimerCarte() {

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




        Image image1 = new Image("/resources/img/lotus.jpg");
        BackgroundSize bSize0 = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);

        Background background1 = new Background(new BackgroundImage(image1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bSize0));

        this.borderpane.setBackground(new Background(new BackgroundImage(image1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bSize0)));

        ArrayList<String> deckList = partie.getListeDeck();
        menuDeck.setItems(FXCollections.observableArrayList( deckList ));
        //menuDeck.setValue(partie.getDeckName());
    }
}
