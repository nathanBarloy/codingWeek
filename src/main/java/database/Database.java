package database;

import json.JSONCardParser;
import json.JSONCardStackParser;
import models.Card;
import models.CardList;
import queries.*;

import seeds.CardStackSeed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private ArrayList<String> listdecks;
    private ArrayList<CardList> listCardList ;
    private ArrayList<Card> listCard;
    private String sessionToken;
    //Construceur
    public Database() {
    listdecks=new ArrayList<String>();
    listCardList = new ArrayList<CardList>();
    listCard = new ArrayList<Card>();
    }
    //------------------------------------------------------------------------------------------------------------------
    //getter
    public ArrayList<String> getListStack() {
        return listdecks;
    }

    public List<CardList> getListCardList() {
        return listCardList;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Setter
    public void setListCardList(ArrayList<CardList> listCardSatck) {
        this.listCardList = listCardList;
    }
    public void setListStack(ArrayList<String> liststack) {
        this.listdecks = liststack;
    }

    //------------------------------------------------------------------------------------------------------------------
    //ajout

    public void add(CardList CardList) {
        this.listCardList.add(CardList);
    }


    public String addCard(String NomDeck, Card card) {
        String a =  "-1";
        //System.out.println("j'ajoute une carte,début");
        for (int  i = 0;i<this.listCardList.size();i++){
            if (this.listCardList.get(i).getName().equals(NomDeck)){
                this.listCardList.get(i).add(card);
                Query query = new QueryAddCard(card);


                    //System.out.println("j'ajoute une carte dans le deck" + NomDeck);
                    //System.out.println();
                    query.send();
                    a = query.getResponse();
                    return a;


            }
        }
        return a;
    }

    public void addCardCardList(String nameCardList , Card card){

        getCardList (nameCardList).get(0).add(card);
    }
    //-----------------------------------------------------------------------------------------------------------------
    public ArrayList<CardList> getCardList (String name){
        ArrayList<CardList> mylistCardList = new ArrayList<CardList>();
        for (CardList cardstack : this.listCardList) {
            if (cardstack.getName().equals(name)) {
                mylistCardList.add(cardstack);
            }
        }
        return mylistCardList;
    }


    //en dur

    public void setDatabase() {
        Query query = new QueryGetCardStackList();
        query.send();
        String JSONresponse= query.getResponse();
        CardList[] cardLists= JSONCardStackParser.JsonToCardStackList(JSONresponse);
        for(CardList c:cardLists)
            this.listCardList.add(c);

        query = new QueryGetCardList();
        query.send();
        JSONresponse= query.getResponse();
        Card[] cards= JSONCardParser.JsonToCardList(JSONresponse);
        for(Card c:cards)
            this.listCard.add(c);

        for(CardList c:listCardList){
            c.setCardStack(this.listCard);

        }





    }

    //

    public String SupressCard(String nomDeck, Card card) {
        if (card != null){
        String a =  "-1";
        for (int  i = 0;i<this.listCardList.size();i++){
            if (this.listCardList.get(i).getName().equals(nomDeck)){
                this.listCardList.get(i).supprime(card);
                Query query = new QueryDelCard(card);
                //System.out.println("je supprime la carte : " + card.getName());


                    query.send();
                    a = query.getResponse();
                    //System.out.println(a);
                    return a;


                }
            }
            return a;
        }
        return null;
    }

    public Card getCard(String temp, String currentDeck) {
        for (CardList c1 : listCardList){
            if (c1.getName().equals((currentDeck))) {
                for (Card c : c1) {
                    if (c.getName().equals(temp)){
                        return c;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<String> getDeckName() {
        return (ArrayList<String>) listdecks;
    }

    public ArrayList<String> getListeCarte(String currentDeck) {
        for (CardList c1 : listCardList){
            if (c1.getName().equals((currentDeck))) {
                return c1.getListeCarte();
            }
        }
        return new ArrayList<String>();
    }

    public void reset() {
        for (CardList c1 : listCardList){
            //System.out.println("c1.size(): "+c1.size());
            c1.resetIndex();
            c1.resetType();
            c1.resetScores();
        }
    }

    public Card pop(String test1) {
        for (CardList c1 : listCardList){
            if (c1.getName().equals(test1)){
                return c1.pop(0);
            }
        }
        return null;
    }


    public void deleteCardList(CardList cardList) {
        this.listCardList.remove(cardList);

    }
    public void deleteCardList(String name) {
        for (CardList cardList : this.listCardList) {
            if (cardList.getName().equals(name)) {
                this.deleteCardList(cardList);
                return;
            }
        }

    }
    public void addDeck(String s, String une_description, String text) {
        this.listCardList.add(new CardList(s,une_description,text));
        System.out.println("addDeck:" + this.listCardList.size());
    }

    public String getFirstDeck() {
        return listCardList.get(0).getName();

    }


    public void setScore(String currentDeck, Card carte,int score) {
        for (CardList cardList : this.listCardList) {
            if (cardList.getName().equals(currentDeck)) {
                for (Card c : cardList){
                    if (c.getName().equals(carte.getName())){
                        c.setState(score);
                    }
                }
            }
        }
    }
}
