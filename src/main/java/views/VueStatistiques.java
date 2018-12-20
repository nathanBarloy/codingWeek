package views;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import launch.Main;
import models.Partie;
import statistic.Stat;

import java.util.Observable;
import java.util.Observer;

public class VueStatistiques implements Observer{
    private String currentDeck = this.partie.getFirstDeck();

    @FXML
    private BorderPane borderpane;

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





            this.comboBox.setItems(FXCollections.observableArrayList(partie.getListeDeck()));

            PieChart1.setPrefHeight(1200);
            PieChart1.setPrefWidth(1200);

            PieChart2.setPrefHeight(1200);
            PieChart2.setPrefWidth(1200);

            PieChart3.setPrefHeight(1200);
            PieChart3.setPrefWidth(1200);

            Stat stat = new Stat();
            double progression = stat.getProgession(partie.getDeck(this.currentDeck));
            PieChart1.getData().setAll(new PieChart.Data("Pourcentage fait",progression ),
                    new PieChart.Data("Non fait", 1 - progression));


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

            Stat stat = new Stat();
            double progression = stat.getProgession(partie.getDeck(this.currentDeck));
            PieChart1.getData().setAll(new PieChart.Data("Pourcentage fait",progression ),
                    new PieChart.Data("Non fait", 1 - progression));


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
