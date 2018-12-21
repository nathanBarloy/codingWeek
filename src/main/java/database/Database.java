package database;

import javafx.scene.control.Alert;
import json.JSONCardParser;
import json.JSONCardStackParser;
import json.JSONListCardListParser;
import models.Card;
import models.CardList;
import queries.*;

import seeds.CardStackSeed;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

import static json.JSONListCardListParser.JsonToListCardList;

public class Database {
    private ArrayList<CardList> listCardList ;
    private ArrayList<Card> listCard;
    private String sessionToken;
    //Construceur
    public Database() {
    listCardList = new ArrayList<CardList>();
    listCard = new ArrayList<Card>();
    sessionToken = "";
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

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getFirstDeck() {
        if (this.listCardList.get(0).getName() != null){
            return listCardList.get(0).getName();
        }
        else{
            return "admin";
        }
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


    public String getSessionToken() {
        return sessionToken;
    }

    public String addCard(String NomDeck, Card card , Boolean local) {
        if (local){
            getCardListString(NomDeck).add(card);
            return "ajout de "+card.getName();
        }
        String a =  "-1";
        //System.out.println("j'ajoute une carte,début");
        for (int  i = 0;i<this.listCardList.size();i++){
            if (this.listCardList.get(i).getName().equals(NomDeck)){
                this.listCardList.get(i).add(card);
                Query query = new QueryAddCard(this,card);

                query.send();
                a = query.getResponse();
                if(a.equals("1")) {
                    System.out.println("Carte ajoutée en ligne");
                    query = new QueryAddCardCardStack(this,card, this.listCardList.get(i));
                    query.send();
                    if (query.getResponse().equals("1")) {
                        System.out.println("Carte ajoutée à la liste en ligne");
                    } else {
                        System.out.println("La carte n'a pas pu être ajoutée à la cardstack.");
                        System.out.println("Erreur : "+query.getResponse());
                    }
                }else if(a.equals("0")){
                    System.out.println("Il existe déjà une carte avec ce nom. Veuillez choisir un autre nom.");
                }else{
                    System.out.println("La carte n'a pas pu être ajoutée en ligne");
                    System.out.println(a);
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

    public void setDatabase(boolean local) {
        System.out.println("debut set database ");
        if (local){
          importDatabaselocal();
          System.out.println("importlocal  ok");
          return;
        }

        else {
            System.out.println("importonline ok");

            Query query = new QueryGetCardStackList(this);

            query.send();
            String JSONresponse = query.getResponse();
            CardList[] cardLists = JSONCardStackParser.JsonToCardStackList(JSONresponse);
            for (CardList c : cardLists)
                this.listCardList.add(c);
            this.listCardList.add(new CardList("admin", "contient toutes les cartes", "admin"));

            query = new QueryGetCardList(this);

            query.send();
            JSONresponse = query.getResponse();
            Card[] cards = JSONCardParser.JsonToCardList(JSONresponse);
            for (Card c : cards)
                this.listCard.add(c);

            for (CardList c : listCardList) {
                c.setCardStack(this.listCard);
                if (c.getName().equals("admin")) {
                    for (Card carte : listCard) {
                        c.add(carte);
                    }
                }
            }
        }

    }


    public String supressCard(String nomDeck, Card card) {
        if (card != null){
        String a =  "-1";
        for (int  i = 0;i<this.listCardList.size();i++){
            if (this.listCardList.get(i).getName().equals(nomDeck)){

                Query query = new QueryDelCard(this,card);

                query.send();
                a = query.getResponse();
                if(a.equals("1")){
                    this.listCardList.get(i).supprime(card);
                    System.out.println("Carte enlevée");
                }

                else if(a.equals("0"))
                    System.out.println("La carte n'a pas pu être retirée");
                else if(a.equals("-1"))
                    System.out.println("Les dépendances n'ont pas pu être retirées");
                else if(a.equals("-3"))
                    System.out.println("Erreur : vous ne pouvez pas supprimer les cartes des autres joueurs");

            return a;


                }
            }
            return a;
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    //version hors ligne
    public void exportDatabaselocal(boolean local) {
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
            this.listCardList = JsonToListCardList(content);
        }catch (IOException e) {
            System.out.println("bug sur l'export de la Base de donné");
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    //



    public void resetScores(String nomdeck) {
        getCardListString(nomdeck).resetScores();
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


    public void deleteCardList(CardList cardList ,boolean local) {
        if (local) {
            this.listCardList.remove(cardList);
            return;
        }

        Query query = new QueryDelCardStack(this,cardList);

        query.send();

        if (query.getResponse().equals("1")) {
            System.out.println("Deck supprimé");
            this.listCardList.remove(cardList);
        } else if (query.getResponse().equals("0")) {
            System.out.println("Le deck n'a pas pu être supprimé");
        } else if (query.getResponse().equals("-1")) {
            System.out.println("La requête n'a pas pu être traitée");
        } else if (query.getResponse().equals("2")) {
            System.out.println("Les dépendences de la cardstack n'ont pas pu être supprimées");
        } else if (query.getResponse().equals("-3")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Vous ne pouvez pas supprimer le deck des autres");
            String message = "";

            alert.setContentText(message);
            alert.showAndWait();
            System.out.println("Vous ne pouvez pas supprimer le deck des autres");
        } else {
            System.out.println("Erreur : "+query.getResponse());
        }

    }
    public void deleteCardList(String name ,boolean local) {
        for (CardList cardList : this.listCardList) {
            if (cardList.getName().equals(name)) {
                this.deleteCardList(cardList , local);
                return;
            }
        }

    }
    public void addDeck(String s, String une_description, String text , boolean local) {

        CardList c= new CardList(s,une_description,text);

        if (local){
            return ;
        }
        Query query = new QueryAddCardStack(this,c);

        query.send();
        if(query.getResponse().equals("1")) {
            this.listCardList.add(c);
            System.out.println("addDeck:" + this.listCardList.size());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Ce deck n'a pas pu être ajouté");
            String message = "";

            alert.setContentText(message);
            alert.showAndWait();
            System.out.println("Erreur d'ajout dans la database");
        }

    }


    public int getScore(String currentDeck, Card c) {
        for (CardList c1 : listCardList){
            if (c1.getName().equals(currentDeck)){
                for (Card carte : c1){
                    if (carte.getName().equals(c.getName())){
                        return c.getState();
                    }
                }
            }
        }
        return -3;
    }


    public CardList getDeck(String currentDeck) {
        for (CardList c : this.listCardList){
            if (c.getName().equals(currentDeck)) {
                return c;
            }
        }
        return null;
    }

    public void setGoodRep(Card carte) {
        for (CardList c : this.listCardList){
            for (Card c1 : c){
                if (c1.getName().equals(carte.getName())){
                    c1.setNbBonnesReponses();
                }
            }
        }
    }
    public void setMediumRep(Card carte) {
        for (CardList c : this.listCardList){
            for (Card c1 : c){
                if (c1.getName().equals(carte.getName())){
                    c1.setNbMoyennesReponses();
                }
            }
        }
    }
    public void setBadRep(Card carte) {
        for (CardList c : this.listCardList){
            for (Card c1 : c){
                if (c1.getName().equals(carte.getName())){
                    c1.setNbFaussesReponses();
                }
            }
        }
    }

    public int getBonnesRep(String deck){
        int s = 0;
        for (CardList c : this.listCardList){
            if (c.getName().equals(deck)) {
                s = 0;
                for (Card c1 : c) {
                    s += c1.getNbBonnesReponses();

                }
            }
        }
        return s;
    }

    public int getMoyennesRep(String deck){
        int s = 0;
        for (CardList c : this.listCardList){
            if (c.getName().equals(deck)) {
                s = 0;
                for (Card c1 : c) {
                    s += c1.getNbMoyennesReponses();

                }
            }
        }
        return s;
    }

    public int getBadRep(String deck) {
        int s = 0;
        for (CardList c : this.listCardList) {
            if (c.getName().equals(deck)) {
                s = 0;
                for (Card c1 : c) {
                    s += c1.getNbFaussesReponses();

                }
            }
        }
        return s;
    }

}
