package learning;

import models.Card;
import models.CardStack;

import java.util.LinkedList;


public class LearningAlgo {
    /*gère la selection des cartes à afficher*/
    private int waitingtime = 10 ;
    private CardStack cardstack;
    private LinkedList<Card> cardlistshow;

    LearningAlgo (CardStack cardstack ){
        this.cardstack = cardstack;
    }

    public Card getCard(){
        cardlistshow  = new LinkedList<Card>();
        return cardstack.pop();

    }


}
