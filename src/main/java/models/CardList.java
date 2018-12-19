package models;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class CardList implements Iterable<Card>{
    private String author;
    private String name;
    private String description;
    private ArrayList<Card> cardStack;
    int index;
    int len;


    public CardList() {
        cardStack = new ArrayList<Card>();
    }

    public CardList(String name, String description, String author) {
        this.author = author;
        this.name = name;
        this.description = description;
        cardStack = new ArrayList<Card>();
        this.index = 0;
        len = 0;
    }

    public CardList(String name, String description, Player author) {
        this.author = author.getUsername();
        this.name = name;
        this.description = description;
        cardStack = new ArrayList<Card>();
        this.index = 0;
        len = 0;
    }

    public CardList(String name, String description) {
        this.name = name;
        this.description = description;
        cardStack = new ArrayList<Card>();
        this.index = 0;
        len = 0;
        this.author="SaltyCard";
    }

    public String getAuthor() {
        return author;
    }

    public int getIndex() {
        return index;
    }

    //copy du stack
    public CardList(CardList another) {
        this.name = another.name;
        this.description = another.description;
        this.cardStack = new ArrayList<Card>();
        this.cardStack = cardStack;
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

    public void add(Card card) {
        cardStack.add(card);
        this.len++;
    }

    public Card pop() {
        if (!(this.index <= len-1)){
            return null;

        }
        else {
            //System.out.println("index =" +index );
            index ++;
            return this.cardStack.get(index-1);

        }
    }
    public void resetIndex(){
        this.index = 0;
    }
    public int getNbCards() {

        return len;
    }


    public double size() {

        return this.cardStack.size();
    }

    public double getSizeCardNotRead() {

        return len-index;
    }



    public Card getCard() {

        if (this.index < len) {

            return this.cardStack.get(index);
        } else return null;
    }


    public Card getnextCard() {
        this.index++;
        if (this.index < len) {

            return this.cardStack.get(index);
        } else return null;
    }

    public Card popCard(int index ) {
        Card card;
        card = this.cardStack.remove(index);
        return card ;
    }





    public boolean endCardList(){
        /*renvoie  true si on a lu toutes les cartes  false sinon*/
        return !(this.index <= len-1);
    }

    @Override
    public String toString() {
        return "CardList{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public Iterator<Card> iterator() {
        return this.cardStack.iterator();
    }

    public ArrayList<String> getListeCarte() {
        ArrayList<String> temp = new ArrayList<String>();
        for (Card c : this.cardStack){
            temp.add(c.getName());
        }
        return temp;
    }

    public void sort(){
        //sort en n² (améliorable)
        ArrayList<Card> mycardstack = new ArrayList<Card>();
        Card min ;
        while (this.cardStack.size() > 0) {
            min = this.cardStack.get(0);
            for (Card card : this.cardStack) {
                if ((card.getUser_fail()+1)/(card.getUser_succes()+1)< (min.getUser_fail()+1/min.getUser_succes()+1)){
                    min = card;
                }
            }
            this.cardStack.remove(min);
            mycardstack.add(min);

        }

    }

    public void supprime(Card card) {
        this.cardStack.remove(card);
    }

    public void resetType() {
        for (Card c : this.cardStack) {
            c.setType("question");
        }
    }

    public CardList generateCardListNotLearn() {
        CardList cardList = new CardList();
        for (Card card : this.cardStack) {
            System.out.println(card.getName() +card.getState());
            if (card.getState()<3) {
                cardList.add(card);
            }
        }
        return cardList;
    }
}
