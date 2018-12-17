

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Card;
import models.CardStack;
import models.Partie;
import models.Player;
import seeds.CardStackSeed;
import views.*;


import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Partie p =  new Partie(new Player(), new CardStack());
        CardStackSeed cardStackSeed = new CardStackSeed(p.getCardStack());
        cardStackSeed.seed();
        p.setCurrentCard(new Card("première carte","Première question",
                "Première réponse"));
        BorderPane root = new BorderPane();

        /*
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/VueCard.fxml"));
        loader.setControllerFactory(iC->new ControllerCard(p));
        Parent VueCarte = loader.load();
        root.setCenter(VueCarte);
        */

        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("views/VueQuestion.fxml"));
        loader2.setControllerFactory(iC->new ControllerVueQuestion(p));
        Parent VueQuestion = loader2.load();
        root.setTop(VueQuestion);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
