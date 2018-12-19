package database;

import models.Card;
import models.CardList;
import queries.Query;
import queries.QueryAddCard;

import seeds.CardStackSeed;

import queries.QueryDelCard;
import queries.QueryDelCardStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<String> listdecks;
    private List<CardList> listCardList ;


    //Construceur
    public Database() {
    listdecks=new ArrayList<String>();
    listCardList = new ArrayList<CardList>();
    }
    //------------------------------------------------------------------------------------------------------------------
    //getter
    public List<String> getListStack() {
        return listdecks;
    }

    public List<CardList> getListCardList() {
        return listCardList;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Setter
    public void setListCardList(List<CardList> listCardSatck) {
        this.listCardList = listCardList;
    }
    public void setListStack(List<String> liststack) {
        this.listdecks = liststack;
    }

    //------------------------------------------------------------------------------------------------------------------
    //ajout

    public void add(CardList CardList) {
        this.listCardList.add(CardList);
    }


    public String addCard(String NomDeck, Card card) {
        String a =  "-1";
        for (int  i = 0;i<this.listCardList.size();i++){
            if (this.listCardList.get(i).getName().equals(NomDeck)){
                this.listCardList.get(i).add(card);
                Query query = new QueryAddCard(card);

                try {
                    query.send();
                    a = query.getResponse();
                    return a;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("bug sur l'envoi de la requête");
                }

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
        CardList cardstack1 = new CardList("test1", "pour test ");
        CardStackSeed cardStackSeed1 = new CardStackSeed(cardstack1);
        cardStackSeed1.seed();
        this.listCardList.add(cardstack1);

        CardList cardstack2 = new CardList("test2", "pour test ");
        CardStackSeed cardStackSeed2 = new CardStackSeed(cardstack2);
        cardStackSeed2.seed();
        this.listCardList.add(cardstack2);

        CardList cardstack3 = new CardList("test3", "pour test ");
        CardStackSeed cardStackSeed3 = new CardStackSeed(cardstack3);
        cardStackSeed3.seed();
        this.listCardList.add(cardstack3);



    }

    //

    public String SupressCard(String nomDeck, Card card) {
        if (card != null){
        String a =  "-1";
        for (int  i = 0;i<this.listCardList.size();i++){
            if (this.listCardList.get(i).getName().equals(nomDeck)){
                this.listCardList.get(i).add(card);
                Query query = new QueryDelCard(card);

                try {
                    query.send();
                    a = query.getResponse();
                    return a;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("bug sur l'envoi de la requête");
                }

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

}
