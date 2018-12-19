package database;

import models.Card;
import models.CardList;
import queries.Query;
import queries.QueryAddCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<String> listdecks;
    private List<CardList> listCardList;


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

    public ArrayList<CardList> getCardList (String name){
        ArrayList<CardList> mylistCardList = new ArrayList<CardList>();
        for (CardList cardstack : this.listCardList) {
            if (cardstack.getName().equals(name)) {
                mylistCardList.add(cardstack);
            }
        }
        return mylistCardList;
    }
}
