package launch;

import database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import learning.LearningAlgo;
import models.Card;
import models.CardStack;
import models.Partie;
import models.Player;
import seeds.CardStackSeed;
import views.*;

import java.io.IOException;

public class  Main extends Application {

    private Stage stage;
    private String name;
    public static Main main;
    private CardStack cardStack;
    private Card card ;
    private Player player;


    @Override
    public void start(Stage primaryStage) throws Exception{
        if (main==null) {
            main = this;
        }

        player=new Player("Nathane","Il est incroyable");
        stage = primaryStage;
        cardStack= new CardStack("Default", "Deck avec des cartes par défaut",player);
        CardStackSeed cs = new CardStackSeed(cardStack, player);
        cs.seed();
        Partie p = new Partie(player,cardStack);


        Parent root = FXMLLoader.load(getClass().getResource("/views/VueLogin.fxml"));
        stage.setTitle("Connexion");
        stage.setScene(new Scene(root, 1000, 800));
        stage.show();

    }

    public void switchScene(String fxmlFile) {
        try {
            if (fxmlFile.equals("/views/VueQuestion.fxml")) {
                cardStack= new CardStack("default","always the same",player);
                CardStackSeed cs = new CardStackSeed(cardStack,player);
                cs.seed();

                Partie p = new Partie(player,cardStack);
                p.setCurrentCard(cardStack.getCard());
                BorderPane root = new BorderPane();
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource("/views/VueQuestion.fxml"));
                loader2.setControllerFactory(iC -> new ControllerVueQuestion(p));
                Parent VueQuestion = null;

                VueQuestion = loader2.load();
                p.init();
                root.setTop(VueQuestion);

                stage.setTitle("Apprentissage");
                stage.setScene(new Scene(root, 1000, 800));
                stage.show();

            }
            if (fxmlFile.equals("/views/VueMenu.fxml")) {
                cardStack= new CardStack("default","always the same",player);
                CardStackSeed cs = new CardStackSeed(cardStack,player);
                cs.seed();

                Partie p = new Partie(player,cardStack);

                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource("/views/VueMenu.fxml"));
                loader2.setControllerFactory(iC -> new VueMenu(p));
                Parent VueMenu = null;

                VueMenu = loader2.load();
                p.init();


                stage.setTitle("Menu Principal");
                stage.setScene(new Scene(VueMenu, 1000, 800));
                stage.show();

            }
            if (fxmlFile.equals("/views/VueCreation.fxml")) {
                cardStack= new CardStack("default","always the same",player);
                CardStackSeed cs = new CardStackSeed(cardStack,player);
                cs.seed();

                Partie p = new Partie(player,cardStack);
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource("/views/VueCreation.fxml"));
                loader2.setControllerFactory(iC -> new VueCreation(p));
                p.init();
                Parent VueCreation = null;

                VueCreation = loader2.load();
                p.init();


                stage.setTitle("Creation");
                stage.setScene(new Scene(VueCreation, 1000, 800));
                stage.show();

            }
            if (fxmlFile.equals("/views/VueLogin.fxml")) {
                Parent root = FXMLLoader.load(getClass().getResource("/views/VueLogin.fxml"));
                stage.setTitle("Connexion");
                stage.setScene(new Scene(root, 1000, 800));
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