package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Partie;
import views.ControllerCard;
import views.ControllerVueQuestion;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Partie p =  new Partie();
        BorderPane root = new BorderPane();


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/VueCard.fxml"));
        loader.setControllerFactory(iC->new ControllerCard(p));
        Parent VueCarte = loader.load();
        root.setCenter(VueCarte);


        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("../views/VueQuestion.fxml"));
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
