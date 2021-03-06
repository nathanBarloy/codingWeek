package launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Card;
import models.CardList;
import models.Partie;
import models.Player;
import seeds.CardStackSeed;
import views.*;

import java.io.IOException;
import java.util.HashMap;

public class  Main extends Application {

    private Stage stage;
    private String name;
    public static Main main;
    private Partie p;



    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Launches the connection window
         */
        if (main==null) {
            main = this;
        }

        p = new Partie();
        stage = primaryStage;

        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/views/VueLogin.fxml"));
        loader2.setControllerFactory(iC -> new VueLogin(p));
        Parent VueLogin = null;

        VueLogin = loader2.load();
        p.init();


        stage.setTitle("Connexion");
        stage.setScene(new Scene(VueLogin, 800, 600));
        stage.show();

    }

    public void switchScene(String fxmlFile,Partie partie){//,String QuestCours,String RepCours) {
        try {

            if (fxmlFile.equals("/views/VueEvalQuestion.fxml")) {

                //p.setCurrentCard(p.getCardList().getCard());
                BorderPane root = new BorderPane();
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new ControllerVueEvalQuestion(p));
                Parent VueQuestion = null;

                VueQuestion = loader2.load();
                p.init();
                root.setTop(VueQuestion);

                stage.setTitle("Apprentissage");
                stage.setScene(new Scene(root, 800, 600));
                stage.show();

            }

            if (fxmlFile.equals("/views/VueQuestion.fxml")) {

                p.setCurrentCard(p.getCardList().getCard());
                BorderPane root = new BorderPane();
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new ControllerVueQuestion(p));
                Parent VueQuestion = null;

                VueQuestion = loader2.load();
                p.init();
                root.setTop(VueQuestion);

                stage.setTitle("Apprentissage");
                stage.setScene(new Scene(root, 800, 600));
                stage.show();

            }
            if (fxmlFile.equals("/views/VueMenu.fxml")) {


                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueMenu(p));
                Parent VueMenu = null;

                VueMenu = loader2.load();
                p.init();


                stage.setTitle("Menu Principal");
                stage.setScene(new Scene(VueMenu, 800, 600));
                stage.show();

            }

            if (fxmlFile.equals("/views/VueCard.fxml")) {

                //System.out.println(partie.getQuestEnCours());
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueCard(partie));
                Parent VueMenu = null;

                VueMenu = loader2.load();
                partie.init();
                //System.out.println(partie.getQuestEnCours());


                stage.setTitle("Vue d'une Carte");
                stage.setScene(new Scene(VueMenu, 800, 600));
                stage.show();

            }

            if (fxmlFile.equals("/views/Decks.fxml")) {


                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueDecks(partie));
                Parent VueDeck = null;

                VueDeck = loader2.load();
                partie.init();


                stage.setTitle("Vue des Decks");
                stage.setScene(new Scene(VueDeck, 800, 600));
                stage.show();

            }





            if (fxmlFile.equals("/views/VueCreation.fxml")) {

                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueCreation(p));
                Parent VueCreation = null;

                VueCreation = loader2.load();
                p.init();


                stage.setTitle("Creation");
                stage.setScene(new Scene(VueCreation, 800, 600));
                stage.show();

            }
            if (fxmlFile.equals("/views/Statistiques.fxml")){
                System.out.println("launching Statistiques");
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueStatistiques(p));
                Parent VueStatistiques = null;

                VueStatistiques = loader2.load();
                p.init();


                stage.setTitle("Statistiques");
                stage.setScene(new Scene(VueStatistiques, 800, 600));
                stage.show();
            }
            if (fxmlFile.equals("/views/VueLogin.fxml")) {
                p = new Partie();
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueLogin(p));
                Parent VueLogin = null;

                VueLogin = loader2.load();
                p.init();


                stage.setTitle("Connexion");
                stage.setScene(new Scene(VueLogin, 800, 600));
                stage.show();
            }
            if (fxmlFile.equals("/views/VueInscription.fxml")) {
                Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
                stage.setTitle("Inscription");
                stage.setScene(new Scene(root, 800, 600));
                stage.show();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchScene(String fxmlFile) {
        try {
            if (fxmlFile.equals("/views/Statistiques.fxml")){
                System.out.println("launching Statistiques");
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueStatistiques(p));
                Parent VueStatistiques = null;

                VueStatistiques = loader2.load();
                p.init();


                stage.setTitle("Statistiques");
                stage.setScene(new Scene(VueStatistiques, 800, 600));
                stage.show();
            }

            if (fxmlFile.equals("/views/VueQuestion.fxml")) {

               // p.setCurrentCard(p.getCardList().getCard());
                BorderPane root = new BorderPane();
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new ControllerVueQuestion(p));
                Parent VueQuestion = null;

                VueQuestion = loader2.load();
                p.init();
                root.setTop(VueQuestion);

                stage.setTitle("Apprentissage");
                stage.setScene(new Scene(root, 800, 600));
                stage.show();

            }
            if (fxmlFile.equals("/views/VueMenu.fxml")) {

                //System.out.println("JE SUIS LA");

                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueMenu(p));
                Parent VueMenu = null;

                VueMenu = loader2.load();


                stage.setTitle("Menu Principal");
                stage.setScene(new Scene(VueMenu, 800, 600));
                p.init();
                stage.show();

            }

            if (fxmlFile.equals("/views/VueCard.fxml")) {



                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueCard(p));
                Parent VueMenu = null;

                VueMenu = loader2.load();
                p.init();
                //System.out.println(p.getQuestEnCours());


                stage.setTitle("Vue d'une Carte");
                stage.setScene(new Scene(VueMenu, 800, 600));
                stage.show();

            }

            if (fxmlFile.equals("/views/Decks.fxml")) {



                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueDecks(p));
                Parent VueDeck = null;

                VueDeck = loader2.load();
                p.init();


                stage.setTitle("Vue des Decks");
                stage.setScene(new Scene(VueDeck, 800, 600));
                stage.show();

            }





            if (fxmlFile.equals("/views/VueCreation.fxml")) {

                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueCreation(p));
                p.init();
                Parent VueCreation = null;

                VueCreation = loader2.load();
                p.init();


                stage.setTitle("Creation");
                stage.setScene(new Scene(VueCreation, 800, 600));
                stage.show();

            }
            if (fxmlFile.equals("/views/VueLogin.fxml")) {
                p = new Partie();
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueLogin(p));
                p.init();
                Parent VueLogin = null;

                VueLogin = loader2.load();
                p.init();


                stage.setTitle("Connexion");
                stage.setScene(new Scene(VueLogin, 800, 600));
                stage.show();
            }
            if (fxmlFile.equals("/views/VueInscription.fxml")) {


                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource(fxmlFile));
                loader2.setControllerFactory(iC -> new VueInscription(p));
                p.init();
                Parent vueInscription = null;


                vueInscription = loader2.load();
                p.init();


                stage.setTitle("Inscription");
                stage.setScene(new Scene(vueInscription, 800, 600));
                stage.show();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void closeStage() {
        stage.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}