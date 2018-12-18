package learning;

import models.Card;
import models.CardStack;

import java.util.LinkedList;


public class LearningAlgo {
    /*gère la selection des cartes à afficher*/
    private int waitingtime = 10 ;
    private CardStack cardstack;
    private LinkedList<Card> cardlistshow;

    public LearningAlgo (CardStack cardstack ) {
        this.cardstack = new CardStack(cardstack);
    }

    public Card getCard(){
        return cardstack.pop();

    }

}
