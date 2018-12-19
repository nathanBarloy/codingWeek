
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

public class ControllerVueQuestion implements Observer {
    private ArrayList<String> liste = new ArrayList<String>();
    private double progress = 0.0;
    private Partie partie;
    private boolean but;
    private int init = -1;
    private int NbBonnesReponses = 0;
    private int NbMoyenReponses = 0;

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
    private static int mdr = -1;
    private boolean done;

    public ControllerVueQuestion(Partie partie) {
        super();
        this.partie = partie;
        this.partie.addObserver(this);
        this.size = this.partie.getCardList().size();
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
        if (RadioParfait.isSelected()){
            this.NbBonnesReponses++;
        }
        if (RadioMoyen.isSelected()){
            this.NbMoyenReponses++;
        }
        if (!this.LabelQuestion.getText().equals("NotStartedYet")){
            partie.valider();
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
                Image image0 = new Image("/resources/img/lotus.jpg");

                //final URL imageURL = getClass().getResource("../ressources/fond");
                //final Image image1 = new Image(imageURL.toExternalForm());

                this.choicebox.setTooltip(new Tooltip("Select the language"));
                this.choicebox.setItems(FXCollections.observableArrayList(
                        new Label("deck1").getText(),
                        "deck 2"
                ));

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

                        alert.setContentText(message);
                        alert.showAndWait();
                    }
                    this.done = true;
                }
                else{
                    //txt = object.getText();
                    System.out.println("choix deck : "+ object);

                }

                Card carte = partie.getCurrentCard(object);
/*
                partie.setDatabase();
                Database database = partie.getDatabase();
                //en dur
                List<CardList>  cardStackList = database.getCardList("test1");
                CardList cardStack = cardStackList.get(0);
                //fin de en dur
                Card carte = cardStack.getCard();
*/


                if (carte != null) {

                    //System.out.println(carte.getQuestion());
                    if (carte.getType().equals("question")) {

                        this.BonneReponsesBarre.setProgress(NbBonnesReponses/size);

                        int a = this.animation2();
                        //int b = this.animation3();
                        //this.PaneAnim.getChildren().remove(img);
                        //this.PaneAnim.getChildren().remove(img);
                        //this.PaneAnim.setVisible(false);

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
                            this.mdr = -1;
                            //System.out.println("label : " + this.LabelQuestion.getText());
                            float f = 0;
                            final Timeline Anim = new Timeline();
                            //timeline.setCycleCount(Timeline.FINITE);
                            Anim.setAutoReverse(false);
                            final KeyValue kv = new KeyValue(this.RondAvancement.progressProperty(), 1);
                            final KeyFrame kf = new KeyFrame(Duration.millis(5000), kv);
                            Anim.getKeyFrames().add(kf);/*
                            Anim.statusProperty().addListener((obs, oldStatus, newStatus) ->
                                    new PauseTransition(Duration.millis(1000))
                                    //System.out.println("here")
                                    //this.mdr++
                                    );*/
                            Anim.setOnFinished(new EventHandler<ActionEvent>() {


                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    RondAvancement.setProgress(0);
                                    try {
                                        partie.valider();
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
                    if (carte.getType().equals("reponse") || this.mdr == 1) {
                        this.mdr = -1;
                        //this.RondAvancement.setProgress(0f);
                        //System.out.println("reponse");
                        this.LabelQuestion.setText(carte.getAnswer());
                    }
                } else {
                    this.progress += 1/size;
                    this.ProgressBar.setProgress(progress);
                    //try {
                    Alert alertt = new Alert(Alert.AlertType.ERROR);
                    alertt.setTitle("ERREUR");
                    alertt.setHeaderText("Il n'y a plus de cartes, nous allons quitter");
                    String mmessage = "";

                    alertt.setContentText(mmessage);
                    alertt.showAndWait();

                     /*   Thread.sleep(3000);
                        Main.main.switchScene("/views/VueMenu.fxml");
                    } catch (InterruptedException e) {
                        System.out.println("exception on waiting");
                        e.printStackTrace();
                    }*/

                }
            }
            this.init = 1;
        }


}
