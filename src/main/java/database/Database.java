package database;

import models.CardStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private List<String> listdecks;
    private List<CardStack>  listCardStack;


    public Database() {
    listdecks=new ArrayList<String>();
    listCardStack = new ArrayList<CardStack>();
    }

    public List<String> getListStack() {
        return listdecks;
    }

    public void setListStack(List<String> liststack) {
        this.listdecks = liststack;
    }

    public List<CardStack> getListCardStack() {
        return listCardStack;
    }

    public void setListCardStack(List<CardStack> listCardSatck) {
        this.listCardStack = listCardStack;
    }

    public void add(CardStack CardStack) {
        this.listCardStack.add(CardStack);
    }

    public List<CardStack> getCardStack (String name){
        List<CardStack> mylistCardStack = new ArrayList<CardStack>();
        for (CardStack cardstack : this.listCardStack) {
            if (cardstack.getName().equals(name)) {
                mylistCardStack.add(cardstack);
                //System.out.println("cardstackname =" +cardstack.getName() + "name = "+ name );

            }
        }
        return mylistCardStack;
    }
}
