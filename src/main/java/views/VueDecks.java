package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import launch.Main;
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
    private boolean getviews;

    public VueDecks(Partie p){
        super();
        this.partie = p;
        this.partie.addObserver(this);
        this.init = true;
    }

    public void Retour(){
        Main.main.switchScene("/views/VueMenu.fxml", this.partie);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (this.init){
            this.comboBox.getItems().addAll(FXCollections.observableArrayList(
                            "Deck 1",
                            "Deck 2 ",
                            "Deck 3"
                    ));
            //tests.
            this.getviews = true;
        }
        if (this.getviews){
            ObservableList<String> items =FXCollections.observableArrayList (
                    "NomCarte1", "NomCarte2", "NomCarte3", "NomCarte4","NomCarte5",
                    "NomCarte6","NomCarte7","NomCarte8");
            this.listeView.setItems(items);
        }
    }
}
