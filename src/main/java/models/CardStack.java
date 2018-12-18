package models;

import launch.Main;

import java.util.LinkedList;

public class CardStack {
    private Player author;
    private String name;
    private String description;
    private LinkedList<Card> cardStack;

    public CardStack(String name, String description, Player author) {
        this.author = author;
        this.name=name;
        this.description=description;
        cardStack = new LinkedList<Card>();
    }

    public Player getAuthor() {
        return author;
    }

    //copy du stack
    public CardStack(CardStack another) {
        this.name=another.name;
        this.description=another.description;
        cardStack = another.cardStack;
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
        if (cardStack.size() > 0){
            return cardStack.pop();
        }
        else{
            return null;
        }
    }

    public int getNbCards(){
        if (cardStack!=null) {
            return cardStack.size();
        }
        return 0;
    }


    public double getSize() {
        return this.cardStack.size();
    }

    public Card getCard() {
        return this.cardStack.getFirst();
    }
}
