
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
    //----------------------------------------------------------------------------------------------
    //Constructeur
    public Partie(Player player, CardStack cardStack) {
        this.player = player;
        this.cardStack = cardStack;

        this.nbCards = cardStack.getNbCards();
    }
    //-----------------------------------------------------------------------------------------------
    //Getter
    public CardStack getCardStack() {

        return cardStack;
    }

    public Card getCurrentCard() {

        return CurrentCard;
    }
    public Card getCurrentCard( CardStack cardStack) {

        return cardStack.pop();
    }

    public Player getPlayer() {
        return player;
    }

    public int getNbCards() {
        return nbCards;
    }

    //-----------------------------------------------------------------------------------------------
    //Setter


    public void setCurrentCard(Card card) {
        this.CurrentCard = card;
    }

    public void setCardStack(CardStack cardStack) {
        this.cardStack = cardStack;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setNbCards(int nbCards) {
        this.nbCards = nbCards;
    }
    //----------------------------------------------------------------------------------------------
    //Autres fonctions

    public void init() {
        setChanged();
        notifyObservers();
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
    public void valider() {

        this.CurrentCard.setType("reponse");
        setChanged();
        notifyObservers();
    }

    public void initImg() {
        setChanged();
        notifyObservers();
    }

    public void buttons() {
        //setChanged();
        //notifyObservers();
    }


}
