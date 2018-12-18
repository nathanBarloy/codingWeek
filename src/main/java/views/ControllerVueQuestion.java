
package views;


import database.Database;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;

import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import launch.Main;
import models.*;

import java.util.*;

public class ControllerVueQuestion implements Observer {
    private ArrayList<String> liste = new ArrayList<String>();
    private double progress = 0.0;
    private Partie partie;
    private boolean but;
    private int init = -1;


    @FXML
    private Pane PaneAnim;

    @FXML
    private Rectangle RecAnim;

    @FXML
    private ProgressIndicator RondAvancement;
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
        //this.progress = -1/this.size;
        this.progress = 0;
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

    public void retour() {
        Main.main.switchScene("/views/VueMenu.fxml");
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
                        "deck 1",
                        "deck 2"
                ));

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
                Card carte = partie.getCurrentCard("test1");




                if (carte != null) {
                    //System.out.println(carte.getQuestion());
                    if (carte.getType().equals("question")) {
                        this.PaneAnim.setVisible(true);
                        Image image0 = new Image("http://www.vanilladome.fr/perso/087/001.png");
                        ImageView img = new ImageView();
                        img.setImage(image0);
                        this.PaneAnim.getChildren().add(img);
                        CubicCurve cubicCurve = new CubicCurve();

                        //Setting properties to cubic curve
                        /*cubicCurve.setStartX(100.0f);
                        cubicCurve.setStartY(150.0f);
                        cubicCurve.setControlX1(400.0f);
                        cubicCurve.setControlY1(40.0f);
                        cubicCurve.setControlX2(175.0f);
                        cubicCurve.setControlY2(250.0f);
                        cubicCurve.setEndX(500.0f);
                        cubicCurve.setEndY(150.0f);*/
                        Path path = new Path();
                        path.getElements().add(new MoveTo(150,150));
                        path.getElements().add(new CubicCurveTo(400, 40, 175, 250, 700, 300));
                        path.getElements().add(new CubicCurveTo(100, 120, 50, 240, 0, -200));
                        path.getElements().add(new CubicCurveTo(0,0,0,0,0,-200));

                        PathTransition pathTransition = new PathTransition();
                        pathTransition.setDuration(Duration.millis(200));
                        pathTransition.setPath(path);
                        pathTransition.setNode(PaneAnim);
                        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        //pathTransition.setCycleCount(Timeline.INDEFINITE);
                        pathTransition.setAutoReverse(true);

                        long startTime = System.currentTimeMillis();
                        long elapsedTime = 0L;
                        pathTransition.play();
                        while (elapsedTime < 1) {
                            //perform db poll/check
                            elapsedTime = (new Date()).getTime() - startTime;
                        }

                        //this.PaneAnim.getChildren().remove(img);
                        this.PaneAnim.setVisible(false);

                        /*
                        final Timeline timeline = new Timeline();
                        //timeline.setCycleCount(Timeline.FINITE);
                        timeline.setAutoReverse(true);
                        final KeyValue kv = new KeyValue(this.RecAnim.scaleYProperty(), 5);
                        final KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
                        timeline.getKeyFrames().add(kf);
                        timeline.play();
                         */

                        this.RondAvancement.setProgress(0.0F);
                        progress += 1/size;
                        this.ProgressBar.setProgress(progress);
                        //System.out.println("question");
                        String temp ="mdr";//= this.LabelQuestion.getText();
                        this.LabelQuestion.setText(carte.getQuestion());
                        if (!temp.equals("NotStartedYet")) {
                            //System.out.println("label : " + this.LabelQuestion.getText());
                            for (int i = 0; i < 3; i++) {
                                    //System.out.println("here");
                                    this.RondAvancement.setProgress(0.25F);
                                    //Thread.sleep(1);
                            }
                        }
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
