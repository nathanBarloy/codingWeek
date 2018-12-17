package models;

import java.util.LinkedList;

public class CardStack {

    private String name;
    private String description;
    private LinkedList<Card> cardStack;

    public CardStack(String name, String description) {
        this.name=name;
        this.description=description;
        cardStack = new LinkedList<Card>();
    }
    public CardStack() {
        cardStack = new LinkedList<Card>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void push(Card card){
        cardStack.push(card);
    }

    public Card pop(){
        return cardStack.pop();
    }

    public int getNbCards(){
        if (cardStack!=null) {
            return cardStack.size();
        }
        return 0;
    }



}
