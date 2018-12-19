package database;

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

    public List<CardList> getCardList (String name){
        List<CardList> mylistCardList = new ArrayList<CardList>();
        for (CardList cardstack : this.listCardList) {
            if (cardstack.getName().equals(name)) {
                mylistCardList.add(cardstack);
                //System.out.println("cardstackname =" +cardstack.getName() + "name = "+ name );

            }
        }
        return mylistCardList;
    }
}
