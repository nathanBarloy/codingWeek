package views;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import launch.Main;
import models.Partie;

import java.util.Observable;
import java.util.Observer;

public class VueStatistiques implements Observer{
    private String currentDeck;
    @FXML
    private ComboBox comboBox;

    @FXML
    private PieChart PieChart1;

    @FXML
    private PieChart PieChart2;

    @FXML
    private PieChart PieChart3;

    private Partie partie;
    private boolean init = true;

    public VueStatistiques(Partie p){
        super();
        this.partie = p;
        this.partie.addObserver(this);
    }
    public void Retour(){
        Main.main.switchScene("/views/VueMenu.fxml");
    }

    public void SwapDeck(){
        this.currentDeck = (String) this.comboBox.getValue();
        this.partie.Choisir();
    }
    @Override
    public void update(Observable o, Object arg) {
        if (this.init) {
            this.comboBox.setItems(FXCollections.observableArrayList(partie.getListeDeck()));
            PieChart1.getData().setAll(new PieChart.Data("Pommes", 50), new PieChart.Data("Oranges", 30),
                    new PieChart.Data("Poires", 25), new PieChart.Data("Pêches", 42),
                    new PieChart.Data("Citrons", 5), new PieChart.Data("Kiwis", 19)
            );
            PieChart2.getData().setAll(new PieChart.Data("Pommes", 50), new PieChart.Data("Oranges", 30),
                    new PieChart.Data("Poires", 25), new PieChart.Data("Pêches", 42),
                    new PieChart.Data("Citrons", 5), new PieChart.Data("Kiwis", 19)
            );
            PieChart3.getData().setAll(new PieChart.Data("Pommes", 50), new PieChart.Data("Oranges", 30),
                    new PieChart.Data("Poires", 25), new PieChart.Data("Pêches", 42),
                    new PieChart.Data("Citrons", 5), new PieChart.Data("Kiwis", 19)
            );
            this.init = false;
        }
        else{
            this.comboBox.setItems(FXCollections.observableArrayList(partie.getListeDeck()));
            PieChart1.getData().setAll(new PieChart.Data("Pommes", 5), new PieChart.Data("Oranges", 30),
                    new PieChart.Data("Poires", 5), new PieChart.Data("Pêches", 2),
                    new PieChart.Data("Citrons", 25), new PieChart.Data("Kiwis", 9)
            );
            PieChart2.getData().setAll(new PieChart.Data("Pommes", 50), new PieChart.Data("Oranges", 30),
                    new PieChart.Data("Poires", 25), new PieChart.Data("Pêches", 42),
                    new PieChart.Data("Citrons", 5), new PieChart.Data("Kiwis", 19)
            );
            PieChart3.getData().setAll(new PieChart.Data("Pommes", 50), new PieChart.Data("Oranges", 30),
                    new PieChart.Data("Poires", 25), new PieChart.Data("Pêches", 42),
                    new PieChart.Data("Citrons", 5), new PieChart.Data("Kiwis", 19)
            );
        }
    }
}
