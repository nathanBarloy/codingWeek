package database;

import models.Card;
import models.CardList;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<String> listdecks;
    private List<CardList> listCardList;


    public Database() {
    listdecks=new ArrayList<String>();
    listCardList = new ArrayList<CardList>();
    }

    public List<String> getListStack() {
        return listdecks;
    }

    public void setListStack(List<String> liststack) {
        this.listdecks = liststack;
    }

    public List<CardList> getListCardList() {
        return listCardList;
    }

    public void setListCardList(List<CardList> listCardSatck) {
        this.listCardList = listCardList;
    }

    public void add(CardList CardList) {
        this.listCardList.add(CardList);
    }
    
    public void add(String NomDeck, Card card) {
        for (int  i = 0;i<this.listCardList.size();i++){
            if (this.listCardList.get(i).getName().equals(NomDeck)){
                this.listCardList.get(i).add(card);
            }
        }

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
