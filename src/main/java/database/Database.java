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
    }

    public List<String> getListStack() {
        return listdecks;
    }

    public void setListStack(List<String> liststack) {
        this.listdecks = listdecks;
    }

    public List<CardStack> getListCardStack() {
        return listCardStack;
    }

    public void setListCardStack(List<CardStack> listCardSatck) {
        this.listCardStack = listCardStack;
    }

    public void addCardStack(CardStack CardStack) {
        this.listCardStack.add(CardStack);
    }
}
