
package views;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import launch.Main;
import models.*;

import javafx.fxml.FXML;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class ControllerVueQuestion implements Observer {
    private double progress = 0.0;
    private Partie partie;
    private boolean but;
    private int init = -1;

    @FXML
    private ProgressBar ProgressBar;

    @FXML
    private ChoiceBox choicebox;
    @FXML
    private RadioButton RadioParfait;

    @FXML
    private RadioButton RadioMoyen;

    @FXML
    private RadioButton RadioIdk;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderpane;

    @FXML
    private BorderPane pane;

    @FXML
    private Label LabelNom;

    @FXML
    private Label LabelQuestion;
    private double size;

    public ControllerVueQuestion(Partie partie) {
        super();
        this.partie = partie;
        this.partie.addObserver(this);
        this.size = this.partie.getCardStack().getSize();
        this.progress = -1/this.size;
    }

    public void ParfaitAction(){
        //this.but = true;
        this.partie.buttons();
        RadioMoyen.setSelected(false);
        RadioIdk.setSelected(false);
    }

    public void MoyenAction(){
        //this.but = true;
        this.partie.buttons();
        RadioParfait.setSelected(false);
        RadioIdk.setSelected(false);
    }

    public void IdkAction(){
        //this.but = true;
        this.partie.buttons();
        RadioParfait.setSelected(false);
        RadioMoyen.setSelected(false);
    }

    public void NvQuest() {
        this.partie.NvQuest();
    }

    public void valider() {

        if (!this.LabelQuestion.getText().equals("NotStartedYet")){
            partie.valider();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
            if (this.init == -1) {
                Image image0 = new Image("https://ma-credence-deco.com/2349-thickbox_default/lotus-et-lumiere-fond-blanc.jpg");

                //final URL imageURL = getClass().getResource("../ressources/fond");
                //final Image image1 = new Image(imageURL.toExternalForm());

                this.choicebox.setTooltip(new Tooltip("Select the language"));
                this.choicebox.setItems(FXCollections.observableArrayList(
                        "New Document", new Separator(),"Open ",
                        new Separator(), "Save", "Save as")
                );

                BackgroundSize bSize0 = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

                Background background1 = new Background(new BackgroundImage(image0,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        bSize0));

                this.anchorPane.setBackground(new Background(new BackgroundImage(image0,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        bSize0)));

            /*
            Image image1 = new Image("http://studio.oiseau-libre.net/Images/_DSC7330-(1).jpg");
            //final URL imageURL = getClass().getResource("../ressources/fond");
            //final Image image1 = new Image(imageURL.toExternalForm());


            BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

            Background background2 = new Background(new BackgroundImage(image1,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    bSize));

            this.pane.setBackground(new Background(new BackgroundImage(image1,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    bSize)));*/
            }

            if (this.init == 1) {
                Card carte = partie.getCurrentCard();
                if (carte != null) {
                    //System.out.println(carte.getQuestion());
                    if (carte.getType().equals("question")) {
                        progress += 1/size;
                        this.ProgressBar.setProgress(progress);
                        //System.out.println("question");
                        this.LabelQuestion.setText(carte.getQuestion());
                    }
                    if (carte.getType().equals("reponse")) {
                        //System.out.println("reponse");
                        this.LabelQuestion.setText(carte.getAnswer());
                    }
                } else {
                    this.progress += 1/size;
                    this.ProgressBar.setProgress(progress);
                    try {
                        Thread.sleep(1000);
                        Main.main.switchScene("/views/VueMenu.fxml");
                    } catch (InterruptedException e) {
                        System.out.println("exception on waiting");
                        e.printStackTrace();
                    }

                }
            }
            this.init = 1;
        }


}
