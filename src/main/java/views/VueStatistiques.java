package views;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import launch.Main;
import models.Partie;
import statistic.Stat;

import java.util.Observable;
import java.util.Observer;

public class VueStatistiques implements Observer{
    private String currentDeck;

    @FXML
    private Button buttonRetour;

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
    private int init = -1;

    public VueStatistiques(Partie p){
        super();
        this.partie = p;
        this.partie.addObserver(this);
    }
    public void Retour(){
        this.init = 20000;
        Main.main.switchScene("/views/VueMenu.fxml");
    }

    public void SwapDeck(){
        this.init = 1;
        this.currentDeck = (String) this.comboBox.getValue();
        this.partie.Choisir();
    }
    @Override
    public void update(Observable o, Object arg) {
        if (this.init == -1) {
            Image image2 = new Image("/resources/img/retour.png");
            ImageView img = new ImageView(image2);
            img.setFitHeight(75);
            img.setFitWidth(125);
            this.buttonRetour.setGraphic(img);

             this.currentDeck = this.partie.getFirstDeck();
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

            int tot = this.partie.getGoodRep(this.currentDeck)
                    + this.partie.getMediumRep(this.currentDeck)
                    + this.partie.getBadRep(this.currentDeck);
            System.out.println("total : " + tot);
            System.out.println("Nb Bonnes r??ponses:" + +this.partie.getGoodRep(this.currentDeck));
            System.out.println("Nb Moyennes r??ponses:" + +this.partie.getMediumRep(this.currentDeck));
            System.out.println("Nb Mauvaises r??ponses:" + +this.partie.getBadRep(this.currentDeck));
            if (tot != 0) {

                PieChart2.getData().setAll(new PieChart.Data("Bonnes R??ponses",
                                this.partie.getGoodRep(this.currentDeck)),
                        new PieChart.Data("R??ponses Moyennes",
                                this.partie.getMediumRep(this.currentDeck)),
                        new PieChart.Data("Mauvaises R??ponses",
                                this.partie.getBadRep(this.currentDeck))
                );
            }
            else{
                PieChart2.getData().setAll(new PieChart.Data("Bonnes R??ponses",
                                0),
                        new PieChart.Data("R??ponses Moyennes",
                                0),
                        new PieChart.Data("Mauvaises R??ponses",
                                0)
                );
            }


            /*
            PieChart3.getData().setAll(new PieChart.Data("Pommes", 50),
                    new PieChart.Data("Oranges", 30)
            );*/
            this.init = 0;
        }
        if(this.init == 1){
            this.comboBox.setItems(FXCollections.observableArrayList(partie.getListeDeck()));Image image2 = new Image("/resources/img/retour.png");
            ImageView img = new ImageView(image2);
            img.setFitHeight(75);
            img.setFitWidth(125);
            this.buttonRetour.setGraphic(img);


            Stat stat = new Stat();
            double progression = stat.getProgession(partie.getDeck(this.currentDeck));
            PieChart1.getData().setAll(new PieChart.Data("Pourcentage fait",progression ),
                    new PieChart.Data("Non fait", 1 - progression));

            int tot = this.partie.getGoodRep(this.currentDeck)
                    + this.partie.getMediumRep(this.currentDeck)
                    + this.partie.getBadRep(this.currentDeck);
            System.out.println("total : " + tot);
            System.out.println("Nb Bonnes r??ponses:" + +this.partie.getGoodRep(this.currentDeck));
            System.out.println("Nb Moyennes r??ponses:" + +this.partie.getMediumRep(this.currentDeck));
            System.out.println("Nb Mauvaises r??ponses:" + +this.partie.getBadRep(this.currentDeck));
            if (tot != 0) {

                PieChart2.getData().setAll(new PieChart.Data("Bonnes R??ponses",
                                this.partie.getGoodRep(this.currentDeck)),
                        new PieChart.Data("R??ponses Moyennes",
                                this.partie.getMediumRep(this.currentDeck)),
                        new PieChart.Data("Mauvaises R??ponses",
                                this.partie.getBadRep(this.currentDeck))
                );
            }
            else{
                PieChart2.getData().setAll(new PieChart.Data("Bonnes R??ponses",
                                0),
                        new PieChart.Data("R??ponses Moyennes",
                                0),
                        new PieChart.Data("Mauvaises R??ponses",
                                0)
                );
            }
        }
    }
}
