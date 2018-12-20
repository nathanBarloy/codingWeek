
package views;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.util.Duration;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import launch.Main;
import models.*;

import java.util.*;

public class ControllerVueEvalQuestion implements Observer {
    private final Timeline timeline = new Timeline();
    private final Timeline Anim = new Timeline();
    private ArrayList<String> liste = new ArrayList<String>();
    private double progress = 0.0;
    private Partie partie;
    private boolean but;
    private int init = -1;
    private int NbBonnesReponses = 0;
    private int NbMoyenReponses = 0;

    @FXML
    private AnchorPane RecPane;


    @FXML
    private TextField Reponse;

    @FXML
    private ProgressBar BonneReponsesBarre;

    @FXML
    private Rectangle RectangleCarte;

    @FXML
    private Pane PaneAnim;

    @FXML
    private Rectangle RecAnim;

    @FXML
    private ProgressIndicator RondAvancement;
    @FXML
    private ProgressBar ProgressBar;

    @FXML
    private ComboBox choicebox;

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
    private static int mdr = -1;
    private boolean done;
    private Card c;
    private String currentDeck;

    public ControllerVueEvalQuestion(Partie partie) {
        super();
        this.partie = partie;
        this.partie.addObserver(this);
        this.size = this.partie.getCardList().size();
        //this.progress = -1/this.size;
        this.progress = 0;
    }

    public void retour() {
        Main.main.switchScene("/views/VueMenu.fxml");
    }

    public void NvQuest() {
        Anim.stop();
        Reponse.setText("");
        this.partie.NvQuest(this.currentDeck);

    }
    public void SwapDeck(){
        this.currentDeck = (String) this.choicebox.getValue();
    }
    public void valider() {
        Anim.stop();
        if (!this.LabelQuestion.getText().equals("NotStartedYet")){
            if (c.getType().equals("question")) {
                partie.valider();
            } else {
                NvQuest();
            }
        }

    }

    public int animation(){
        this.PaneAnim.setVisible(true);
        Image image0 = new Image("/resources/img/hand.png");
        ImageView img = new ImageView();
        img.setImage(image0);
        img.setFitHeight(250);
        img.setFitWidth(250);
        this.PaneAnim.getChildren().add(img);
        CubicCurve cubicCurve = new CubicCurve();
        Path path = new Path();
        path.getElements().add(new MoveTo(150,150));
        path.getElements().add(new CubicCurveTo(400, 40, 175, 250, 400, 100));
        path.getElements().add(new CubicCurveTo(100, 120, 50, 240, -100, -200));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(750));
        pathTransition.setPath(path);
        pathTransition.setNode(PaneAnim);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        //pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);

        pathTransition.play();

        return 1;

    }

    public int animation2(){
        this.LabelQuestion.setText("");
        final Timeline timeline = new Timeline();
        //timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(this.RectangleCarte.scaleYProperty(), 0.01);
        final KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
        final KeyValue kv2 = new KeyValue(this.RectangleCarte.scaleXProperty(), 0.01);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(200), kv2);
        timeline.getKeyFrames().add(kf);
        timeline.getKeyFrames().add(kf2);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent actionEvent) {
                final Timeline timeline2 = new Timeline();
                timeline2.setAutoReverse(true);
                final KeyValue kv = new KeyValue(RectangleCarte.scaleXProperty(), 1);
                final KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
                final KeyValue kv2 = new KeyValue(RectangleCarte.scaleYProperty(), 1);
                final KeyFrame kf2 = new KeyFrame(Duration.millis(200), kv2);
                timeline2.getKeyFrames().add(kf);
                timeline2.getKeyFrames().add(kf2);
                timeline2.play();
                timeline2.setOnFinished(new EventHandler<ActionEvent>() {


                    @Override
                    public void handle(ActionEvent actionEvent) {
                        LabelQuestion.setText(c.getQuestion());
                    }
                });

            }
        });
        timeline.play();
        return 1;
    }

    public int animation3(){
        final Timeline timeline2 = new Timeline();
        timeline2.setAutoReverse(true);
        final KeyValue kv2 = new KeyValue(this.RectangleCarte.scaleYProperty(), 1000);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
        timeline2.getKeyFrames().add(kf2);
        timeline2.play();
        return 1;
    }



    @Override
    public void update(Observable o, Object arg) {
        if (this.init == -1) {
            this.partie.reset();
            Image image0 = new Image("/resources/img/lotus.jpg");

            //final URL imageURL = getClass().getResource("../ressources/fond");
            //final Image image1 = new Image(imageURL.toExternalForm());

            this.choicebox.getItems().addAll(this.partie.getListeDeck());

            BackgroundSize bSize0 = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);

            Background background1 = new Background(new BackgroundImage(image0,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    bSize0));

            this.anchorPane.setBackground(new Background(new BackgroundImage(image0,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    bSize0)));
        }

        if (this.init == 1) {
            String txt = "";
            String object = (String) this.choicebox.getValue();

            if (object == null){
                if(this.done == false) {

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ATTENTION");

                    alert.setHeaderText("Vous n'avez pas choisi de deck, le deck 1 est pris par défaut.");
                    String message = "";
                    //alert.setHeaderText("Choisissez un deck avant de commencer");
                    //String message = "Vous ne pouvez pas commencer si vous n'avez pas choisi de deck";

                    alert.setContentText(message);
                    alert.showAndWait();
                }
                //this.done = true;
            }
            else{
                //txt = object.getText();
                System.out.println("choix deck : "+ object);

            }

            Card carte = partie.getCurrentCard(this.currentDeck);
            this.c = carte;

            if (carte != null) {

                if (carte.getType().equals("question")) {
                    this.partie.timeout = true;

                    this.BonneReponsesBarre.setProgress(NbBonnesReponses/size);

                    int a = this.animation2();

                    this.RondAvancement.setProgress(0.0F);
                    progress += 1/size;
                    this.ProgressBar.setProgress(progress);
                    //System.out.println("question");
                    String temp ="mdr";//= this.LabelQuestion.getText();
                    //
                    if (!temp.equals("NotStartedYet")) {
                        this.mdr = -1;
                        //System.out.println("label : " + this.LabelQuestion.getText());
                        float f = 0;
                        //timeline.setCycleCount(Timeline.FINITE);
                        Anim.setAutoReverse(false);
                        final KeyValue kv = new KeyValue(this.RondAvancement.progressProperty(), 1);
                        final KeyFrame kf = new KeyFrame(Duration.millis(15000), kv);
                        Anim.getKeyFrames().add(kf);
                        Anim.setOnFinished(new EventHandler<ActionEvent>() {


                            @Override
                            public void handle(ActionEvent actionEvent) {
                                RondAvancement.setProgress(0);
                                try {
                                    partie.timeout = false;
                                    //partie.valider();
                                }
                                catch (Exception e){
                                    //System.out.println("caught exception");
                                }
                                //System.out.println("finished");
                            }
                        });
                        Anim.play();
                    }
                }
                if (carte.getType().equals("reponse")) {
                    this.mdr = -1;
                    //this.RondAvancement.setProgress(0f);
                    //System.out.println("reponse");
                    String temp = "";
                    temp =  (String) this.Reponse.getText();
                    if (temp == null){
                        temp = " ";
                    }
                    String rep = carte.getAnswer();
                    if(partie.verifierReponse(rep,temp)){

                        this.NbBonnesReponses++;
                        this.partie.setScore(this.currentDeck,this.c,1);

                        Image image0 = new Image("/resources/img/smiley.png");
                        ImageView img = new ImageView();
                        img.setImage(image0);
                        img.setFitHeight(250);
                        img.setFitWidth(250);
                        img.setX(-100);
                        img.setY(-100);


                        if (this.partie.timeout) {
                            this.RecPane.getChildren().add(img);
                        }
                        timeline.setAutoReverse(true);
                        KeyValue kv = new KeyValue(this.RecPane.rotateProperty(), 0);
                        KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
                        timeline.getKeyFrames().add(kf);
                        timeline.setOnFinished(new EventHandler<ActionEvent>() {


                            @Override
                            public void handle(ActionEvent actionEvent) {
                                RecPane.getChildren().remove(img);
                            }
                        });
                            timeline.play();
                        this.partie.timeout = false;
                    } else {

                        this.partie.setScore(this.currentDeck,this.c,-1);
                        Image image0 = new Image("/resources/img/SmileyTriste.png");
                        ImageView img = new ImageView();
                        img.setImage(image0);
                        img.setFitHeight(250);
                        img.setFitWidth(250);
                        img.setX(-100);
                        img.setY(-100);


                        if (this.partie.timeout) {
                            this.RecPane.getChildren().add(img);
                        }
                        Timeline timeline = new Timeline();
                        timeline.setAutoReverse(true);
                        KeyValue kv = new KeyValue(this.RecPane.rotateProperty(), 0);
                        KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
                        timeline.getKeyFrames().add(kf);
                        timeline.setOnFinished(new EventHandler<ActionEvent>() {


                            @Override
                            public void handle(ActionEvent actionEvent) {
                                RecPane.getChildren().remove(img);
                            }
                        });
                        timeline.play();
                        this.partie.timeout = false;
                    }
                    this.LabelQuestion.setText(carte.getAnswer());
                }
            } else {
                this.progress += 1/size;
                this.ProgressBar.setProgress(progress);
                this.init = 1000;
                this.partie.reset();

            }
        }
        if (this.init == -1) {
            this.init = 1;
        }
    }


}
