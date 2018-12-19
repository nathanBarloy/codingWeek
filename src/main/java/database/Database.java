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
    private ArrayList<String> listdecks;
    private ArrayList<CardList> listCardList ;


    //Construceur
    public Database() {
    listdecks=new ArrayList<String>();
    listCardList = new ArrayList<CardList>();
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

                try {
                    //System.out.println("j'ajoute une carte dans le deck" + NomDeck);
                    //System.out.println();
                    query.send();
                    a = query.getResponse();
                    return a;
                } catch (IOException e) {
                    e.printStackTrace();
                    //System.out.println("bug sur l'envoi de la requête");
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
        cardStackSeed1.seed(cardstack1.getName());
        this.listCardList.add(cardstack1);

        CardList cardstack2 = new CardList("test2", "pour test ");
        CardStackSeed cardStackSeed2 = new CardStackSeed(cardstack2);
        cardStackSeed2.seed(cardstack2.getName());
        this.listCardList.add(cardstack2);

        CardList cardstack3 = new CardList("test3", "pour test ");
        CardStackSeed cardStackSeed3 = new CardStackSeed(cardstack3);
        cardStackSeed3.seed(cardstack3.getName());
        this.listCardList.add(cardstack3);



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

                try {
                    query.send();
                    a = query.getResponse();
                    //System.out.println(a);
                    return a;
                } catch (IOException e) {
                    e.printStackTrace();
                    //System.out.println("bug sur l'envoi de la requête");
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

    public void reset() {
        for (CardList c1 : listCardList){
            //System.out.println("c1.size(): "+c1.size());
            c1.resetIndex();
            c1.resetType();
        }
    }

    public Card pop(String test1) {
        for (CardList c1 : listCardList){
            if (c1.getName().equals(test1)){
                return c1.pop();
            }
        }
        return null;
    }
}
