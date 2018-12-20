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
    private ComboBox comboBox;

    @FXML
    private ListView listeView;


    private Partie partie;
    private boolean init;
    private boolean choix = false;
    private String CurrentDeck;
    private boolean supress = false;

    public VueDecks(Partie p){
        super();
        this.partie = p;
        this.partie.addObserver(this);
        this.init = true;
    }

    public void Choisir(){
        this.choix = true;
        //String temp = (String) this.listeView.getItems().get(listeView.getEditingIndex());
        String temp = (String) this.comboBox.getValue();
        if (temp != null){
            this.CurrentDeck = temp;
            this.choix = true;
            this.partie.Choisir();
        }
        else{
            /*Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Vous n'avez pas choisi de deck");
            String message = "";

            alert.setContentText(message);
            alert.showAndWait();*/
        }

    }
    public void SupprimerCarte(){
        this.supress= true;
        String temp = (String) listeView.getSelectionModel().getSelectedItem();
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
                this.partie.SupprimerCard(this.CurrentDeck, this.partie.getCard(temp,this.CurrentDeck));
            }
        }
    }

    public void supprimerDeck() {
        String temp = (String) this.comboBox.getValue();
        if (temp!=null) {
            // Supprime le deck FDP
            partie.deleteCardList(temp);
            comboBox.setItems(FXCollections.observableArrayList(partie.getListeDeck()));
            comboBox.setValue(null);
        }
    }

    public void resetStat() {
        partie.resetScores(CurrentDeck);
    }


    public void Retour(){
        Main.main.switchScene("/views/VueMenu.fxml", this.partie);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (this.init){

            this.comboBox.setItems(FXCollections.observableArrayList(partie.getListeDeck()));

            //tests.
            //this.getviews = true;
            this.init = false;
        }
        if (this.choix){
            //System.out.println("here");
            ObservableList<String> items =FXCollections.observableArrayList ();
            items.addAll(this.partie.getListeCarte(CurrentDeck));
            //System.out.println(items.toString());
            //System.out.println("items:"+items.toString());
            this.listeView.setItems(items);
            this.choix = false;
        }
        if (this.supress){
            ObservableList<String> items =FXCollections.observableArrayList ();
            items.addAll(this.partie.getListeCarte(CurrentDeck));
            this.listeView.setItems(items);
            supress = false;
        }
    }
}
