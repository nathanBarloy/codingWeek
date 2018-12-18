package launch;

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

import java.io.IOException;

public class  Main extends Application {

    private Stage stage;
    public static Main main;


    @Override
    public void start(Stage primaryStage) throws Exception{
        if (main==null) {
            main = this;
        }

        stage = primaryStage;
        CardStack c = new CardStack();
        CardStackSeed cs = new CardStackSeed(c);
        cs.seed();
        Partie p = new Partie(new Player(),c);

        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/views/VueMenu.fxml"));
        loader2.setControllerFactory(iC -> new VueMenu(p));
        Parent VueMenu = null;

        VueMenu = loader2.load();
        p.init();


        stage.setTitle("Menu Principal");
        stage.setScene(new Scene(VueMenu, 1000, 800));
        stage.show();

        /*
        Parent root = FXMLLoader.load(getClass().getResource("/views/VueMenu.fxml"));
        stage.setTitle("Menu Principal");
        stage.setScene(new Scene(root, 1000, 800));
        stage.show();
        */
    }

    public void switchScene(String fxmlFile) {
        try {
            if (fxmlFile.equals("/views/VueQuestion.fxml")) {
                CardStack c = new CardStack();
                CardStackSeed cs = new CardStackSeed(c);
                cs.seed();

                Partie p = new Partie(new Player(),c);
                p.setCurrentCard(c.getCard());
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
                CardStack c = new CardStack();
                CardStackSeed cs = new CardStackSeed(c);
                cs.seed();
                Partie p = new Partie(new Player(),c);

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
                Parent root = FXMLLoader.load(getClass().getResource("/views/VueCreation.fxml"));

                stage.setTitle("Creation");
                stage.setScene(new Scene(root, 1000, 800));
                stage.show();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void closeStage() {
        stage.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}