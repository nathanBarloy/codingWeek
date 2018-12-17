package SaltyCard.src.main.java.models;

import java.util.LinkedList;

public class CardStack {

    private LinkedList<Card> cardStack;

    public CardStack() {
        cardStack = new LinkedList<Card>();
    }


    public void push(Card card){
        cardStack.push(card);
    }

    public Card pop(){
        return cardStack.pop();
    }

    public int getNbCards(){
        return cardStack.size();
    }
}
