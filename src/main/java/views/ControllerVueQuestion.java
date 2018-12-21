
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
    final Timeline Anim = new Timeline();
    private ArrayList<String> liste = new ArrayList<String>();
    private double progress = 0.0;
    private Partie partie;
    private boolean but;
    private int init = -1;
    private int NbBonnesReponses = 0;
    private int NbMoyenReponses = 0;
    private int NbIdkReponses;

    @FXML
    private Label username;

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
    private boolean AChange;
    private String currentDeck;
    private Card carte;


    public ControllerVueQuestion(Partie partie) {
        super();
        this.partie = partie;
        this.partie.addObserver(this);
        //this.size = this.partie.getCardList().size();
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

    public void retour() throws InterruptedException {
        this.init = 10000;
        this.ProgressBar.setProgress(1);
        Thread.sleep(500);
        Main.main.switchScene("/views/VueMenu.fxml");
    }

    public void NvQuest() {
        if (RadioParfait.isSelected()){
            this.partie.setScore(this.currentDeck,this.carte,3);
            this.NbBonnesReponses++;
            this.partie.setGoodRep(this.carte);
        }
        if (RadioMoyen.isSelected()){
            this.partie.setScore(this.currentDeck,this.carte,1);
            this.NbMoyenReponses++;
            this.partie.setMediumRep(this.carte);
        }
        if (RadioIdk.isSelected()){

            this.partie.setScore(this.currentDeck,this.carte,0);

            this.NbIdkReponses++;
            this.partie.setBadRep(this.carte);
        }
        if (this.currentDeck == null){/*
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ATTENTION");

            alert.setHeaderText("Vous n'avez pas choisi de deck, le deck 1 est pris par défaut.");
            String message = "";

            alert.setContentText(message);
            alert.showAndWait();*/
            this.currentDeck = this.partie.getFirstDeck();
        }
        this.Anim.stop();
        this.partie.NvQuest(this.currentDeck);
    }

    public void valider() {
        this.Anim.stop();

        if (!this.LabelQuestion.getText().equals("NotStartedYet")){
            partie.valider();
        }
    }

    public void SwapDeck(){
        this.partie.reset();
        this.currentDeck = (String) this.choicebox.getValue();
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

    public void goStat() {
        Main.main.switchScene("/views/Statistiques.fxml");
    }



    @Override
    public void update(Observable o, Object arg) {
            if (this.init == -1) {
                this.partie.reset();
                Image image0 = new Image("/resources/img/lotus.jpg");

                //final URL imageURL = getClass().getResource("../ressources/fond");
                //final Image image1 = new Image(imageURL.toExternalForm());

                this.choicebox.getItems().addAll(this.partie.getListeDeck());
                username.setText("utilisateur : " + partie.getPlayer().getUsername());

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

                this.carte = partie.getCurrentCard(currentDeck);






                if (carte != null) {
                    if (carte.getType().equals("question")) {
                        int tot = this.partie.getGoodRep(this.currentDeck)
                                + this.partie.getMediumRep(this.currentDeck)
                                + this.partie.getBadRep(this.currentDeck);
                        if (tot != 0) {
                            System.out.println("nb bonnes rep :" + this.partie.getGoodRep(this.currentDeck));
                            System.out.println("nb tot :" + tot );
                            this.BonneReponsesBarre.setProgress(((double)this.partie.getGoodRep(this.currentDeck))/
                                    (double) tot);
                        }
                        int a = this.animation2();
                        this.RondAvancement.setProgress(0.0F);

                        //gestion de ma barre de progression
                        //progress += 1/size;
                        //System.out.println(this.partie.getDeckEnCours());
                        //System.out.println(this.currentDeck);
                        //progress =
                       // System.out.println(this.partie.getProgressCurrentDeck());
                        progress = this.partie.getProgressCurrentDeck();
                        //gfin de la gestion de la barre de progression
                        this.ProgressBar.setProgress(progress);

                        //System.out.println("question");
                        String temp ="mdr";//= this.LabelQuestion.getText();
                        this.LabelQuestion.setText(carte.getQuestion());
                        if (!temp.equals("NotStartedYet")) {
                            this.mdr = -1;
                            //System.out.println("label : " + this.LabelQuestion.getText());
                            float f = 0;
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
                                        valider();
                                    }
                                    catch (Exception e){
                                        //System.out.println("caught exception");
                                    }
                                    //System.out.println("finished");
                                }
                            });
                            this.AChange = false;
                            Anim.play();
                        }
                    }
                    if (carte.getType().equals("reponse")) {
                        //this.RondAvancement.setProgress(0f);
                        //System.out.println("reponse :" + carte.getName());

                        this.LabelQuestion.setText(carte.getAnswer());
                    }
                } else {
                    //System.out.println("here");
                    //setpartie progress
                    this.progress =   0 ;//this.partie.getProgressCurrentDeck();
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
