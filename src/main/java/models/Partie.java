
package models;
import launch.Main;
import models.*;
import views.ControllerVueQuestion;
import models.Player;

import java.util.Observable;

public class Partie extends Observable{
    private Player player;
    private CardStack cardStack;
    private int nbCards;
    private Card CurrentCard;

    public CardStack getCardStack() {
        return cardStack;
    }

    public Partie(Player player, CardStack cardStack) {
        this.player = player;
        this.cardStack = cardStack;
        this.nbCards = cardStack.getNbCards();
    }

    public Card getCurrentCard() {
        return CurrentCard;
    }

    public void valider() {

        this.CurrentCard.setType("reponse");
        setChanged();
        notifyObservers();
    }

    public void init() {
        setChanged();
        notifyObservers();
    }

    public void setCurrentCard(Card card) {
        this.CurrentCard = card;
    }

    public void NvQuest() {
        this.CurrentCard = cardStack.pop();
        if (this.CurrentCard != null) {
            this.CurrentCard.setType("question");
            setChanged();
            notifyObservers();
        } else {
            Main.main.switchScene("/views/VueMenu.fxml");
        }
    }

    public void initImg() {
        setChanged();
        notifyObservers();
    }
}
