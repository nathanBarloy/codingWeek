package learning;

import models.Card;
import models.CardList;

import java.util.ArrayList;

public class LearningAlgo {
    /*gère la selection des cartes à afficher*/
    private int waitingtime = 10 ;
    private CardList cardstack;
    private ArrayList<Card> cardlistshow;

    public LearningAlgo (CardList cardstack ) {
        this.cardstack = new CardList(cardstack);
    }

    public Card getCard(){
        System.out.println(cardstack.getCard().getName());
        return cardstack.getCard();

    }

}
