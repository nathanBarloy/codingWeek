package database;

import json.JSONCardParser;
import json.JSONCardStackParser;
import json.JSONListCardListParser;
import models.Card;
import models.CardList;
import queries.*;

import seeds.CardStackSeed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Database {
    private ArrayList<CardList> listCardList ;
    private ArrayList<Card> listCard;
    private String sessionToken;
    //Construceur
    public Database() {
    listCardList = new ArrayList<CardList>();
    listCard = new ArrayList<Card>();
    }
    //------------------------------------------------------------------------------------------------------------------
    //getter
    public ArrayList<CardList> getListCardList() {
        return listCardList;
    }

    public ArrayList<CardList> getCardList (String name){
        ArrayList<CardList> mylistCardList = new ArrayList<CardList>();
        for (CardList cardstack : this.listCardList) {
            if (cardstack.getName().equals(name)) {
                mylistCardList.add(cardstack);
            }
        }
        return mylistCardList;
    }


    public String getFirstDeck() {
        return listCardList.get(0).getName();
    }

    public ArrayList<String> getDeckName() {
        ArrayList<String> listdecks = new ArrayList<String>();
        for (CardList cardList : this.listCardList) {
            listdecks.add(cardList.getName());
        }
        return  listdecks;
    }

    public ArrayList<String> getListeCarte(String currentDeck) {
        for (CardList c1 : listCardList){
            if (c1.getName().equals((currentDeck))) {
                return c1.getListeCarte();
            }
        }
        return new ArrayList<String>();
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

    public CardList getCardListString( String name ) {
        for (CardList cardList : this.listCardList)  {
            if (cardList.getName().equals(name)) {
                return cardList ;
            }
        }
        return null;
    }
    //------------------------------------------------------------------------------------------------------------------
    //Setter
    public void setListCardList(ArrayList<CardList> listCardSatck) {
        this.listCardList = listCardList;
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
                query.send();
                a = query.getResponse();
                if(a.equals("1")) {
                    System.out.println("Carte ajoutée en ligne");
                    query = new QueryAddCardCardStack(card, this.listCardList.get(i));
                    query.send();
                    if (query.getResponse().equals("1")) {
                        System.out.println("Carte ajoutée à la liste en ligne");
                    } else {
                        System.out.println("La carte n'a pas pu être ajoutée à la cardstack.");
                    }
                }else{
                    System.out.println("La carte n'a pas pu être ajoutée en ligne");
                }
                return a;


            }
        }
        return a;
    }

    public void addCardCardList(String nameCardList , Card card){

        getCardList (nameCardList).get(0).add(card);
    }
    //-----------------------------------------------------------------------------------------------------------------
    //Gestion en ligne

    public void setDatabase() {
        Query query = new QueryGetCardStackList();
        query.send();
        String JSONresponse= query.getResponse();
        CardList[] cardLists= JSONCardStackParser.JsonToCardStackList(JSONresponse);
        for(CardList c:cardLists)
            this.listCardList.add(c);
        this.listCardList.add(new CardList("admin","contient toutes les cartes","admin"));

        query = new QueryGetCardList();
        query.send();
        JSONresponse= query.getResponse();
        Card[] cards= JSONCardParser.JsonToCardList(JSONresponse);
        for(Card c:cards)
            this.listCard.add(c);

        for(CardList c:listCardList){
            c.setCardStack(this.listCard);
            if (c.getName().equals("admin")) {
                for(Card carte : listCard){
                    c.add(carte);
                }
            }
        }


    }


    public String supressCard(String nomDeck, Card card) {
        if (card != null){
        String a =  "-1";
        for (int  i = 0;i<this.listCardList.size();i++){
            if (this.listCardList.get(i).getName().equals(nomDeck)){
                this.listCardList.get(i).supprime(card);
                Query query = new QueryDelCard(card);
                query.send();
                a = query.getResponse();
                if(a.equals("1"))
                    System.out.println("Carte enlevée");
                else if(a.equals("0"))
                    System.out.println("La carte n'a pas pu être retirée");
                else if(a.equals("-1"))
                    System.out.println("Les dépendances n'ont pas pu être retirées");
            return a;


                }
            }
            return a;
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    //version hors ligne
    public void exportDatabaselocal() {

        JSONListCardListParser jsonListCardListParser = new JSONListCardListParser() ;
        try {
            JSONListCardListParser.ListCardListToJson(this.listCardList);
        }catch (IOException e) {
            System.out.println("bug sur l'export de la Base de donné");
        }
    }
    public void importDatabaselocal() {
        String filePath = "JSONS/listCardList.json";
        String content = "";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(content);
        JSONListCardListParser jsonListCardListParser = new JSONListCardListParser() ;
        try {
            this.listCardList = JSONListCardListParser.JsonToListCardList(content);
        }catch (IOException e) {
            System.out.println("bug sur l'export de la Base de donné");
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    //


    public void resetScores(String nomdeck) {

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

        CardList c= new CardList(s,une_description,text);
        this.listCardList.add(c);
        Query query = new QueryAddCardStack(c);
        query.send();
        if(query.getResponse().equals("1"))
            System.out.println("addDeck:" + this.listCardList.size());
        else
            System.out.println("Erreur d'ajout dans la database");

    }




}
