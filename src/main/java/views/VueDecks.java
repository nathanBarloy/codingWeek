package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import launch.Main;
import models.Card;
import models.Partie;

import java.util.Observable;
import java.util.Observer;

public class VueDecks implements Observer {
    @FXML
    private ComboBox BoxSupprimer;

    @FXML
    private ComboBox comboBox;

    @FXML
    private ListView listeView;


    private Partie partie;
    private boolean init;
    private boolean choix = false;
    private String CurrentDeck;

    public VueDecks(Partie p){
        super();
        this.partie = p;
        this.partie.addObserver(this);
        this.init = true;
    }

    public void Choisir(){
        String temp = (String) this.comboBox.getValue();
        if (temp != null){
            this.CurrentDeck = temp;
            this.choix = true;
            this.partie.Choisir();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Vous n'avez pas choisi de deck");
            String message = "";

            alert.setContentText(message);
            alert.showAndWait();
        }

    }
    public void SupprimerCarte(){
        String temp = (String) this.BoxSupprimer.getValue();
        if (temp ==  null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Vous n'avez pas choisi de carte à supprimer");
            String message = "";

            alert.setContentText(message);
            alert.showAndWait();
        }

        else{
            if (CurrentDeck == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Vous n'avez pas choisi de deck");
                String message = "";

                alert.setContentText(message);
                alert.showAndWait();
            }
            else {
                this.partie.getCard(temp,CurrentDeck);
                this.partie.SupprimerCard(this.CurrentDeck, this.partie.getCard(temp,this.CurrentDeck));
            }
        }
    }
    public void Retour(){
        Main.main.switchScene("/views/VueMenu.fxml", this.partie);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (this.init){
            this.comboBox.getItems().addAll(FXCollections.observableArrayList(
                            "Deck 1",
                            "test1",
                            "Deck 3"
                    ));
            //tests.
            //this.getviews = true;
            this.init = false;
        }
        if (this.choix){
            System.out.println("here");
            ObservableList<String> items =FXCollections.observableArrayList ();
            items.addAll(this.partie.getListeCarte(CurrentDeck));
            if (this.CurrentDeck.equals("Deck 1")) {
                items.add("test1");
            }
            System.out.println("items:"+items.toString());
            this.listeView.setItems(items);
            this.BoxSupprimer.setItems(items);
        }
    }
}
