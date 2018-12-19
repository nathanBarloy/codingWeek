package database;

import models.CardList;

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
