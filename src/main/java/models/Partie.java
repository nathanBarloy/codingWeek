
package models;
import models.*;
import views.ControllerVueQuestion;
import models.Player;

public class Partie {
    private Player player;
    private CardStack cardStack;
    private int nbCards;

    public Partie(Player player, CardStack cardStack) {
        this.player = player;
        this.cardStack = cardStack;
        this.nbCards = cardStack.getNbCards();
    }


    public void addObserver(ControllerVueQuestion controllerVueQuestion) {

    }
}
