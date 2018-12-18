package models;

import launch.Main;

import java.util.ArrayList;

public class CardStack {
    private Player author;
    private String name;
    private String description;
    private ArrayList<Card> cardStack;
    int index;
    int len;



    public CardStack() {
        cardStack = new ArrayList<Card>();
    }
    public CardStack(String name, String description, Player author) {
        this.author = author;
        this.name=name;
        this.description=description;
        cardStack = new ArrayList<Card>();
        this.index =0;
        len =0 ;
    }

    public Player getAuthor() {
        return author;
    }

    //copy du stack
    public CardStack(CardStack another) {
        this.name=another.name;
        this.description=another.description;
        this.cardStack = new ArrayList<Card>();
        this.cardStack = another.cardStack;
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

    public void add(Card card){
        cardStack.add(card);
        this.len ++;
    }

    public Card pop(){
        if (this.index < len){
            return cardStack.get(index);
        }
        else{
            return null;
        }
    }

    public int getNbCards(){

        return len;
    }


    public double getSize() {
        return this.cardStack.size();
    }

    public Card getCard() {

        if (this.index < len) {

            return this.cardStack.get(index);
        }

        else return null;
    }
    public Card getnextCard() {
        index ++;
        if (this.index < len) {

            return this.cardStack.get(index);
        }

        else return null;
    }
}
