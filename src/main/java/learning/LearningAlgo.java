package learning;

import models.Card;
import models.CardList;

import java.util.ArrayList;


public class LearningAlgo {
    /*gère la selection des cartes à afficher*/
    private CardList cardList;
    private CardList cardlistshow;

    public LearningAlgo (CardList cardList) {

        this.cardList = cardList; //faire une cardList.copy
    }
    //------------------------------------------------------------------------------------------------------------------
    //getter

    public CardList getCardList() {
        return cardList;
    }

    //------------------------------------------------------------------------------------------------------------------
    //genere si y'a des données existentes
    public  CardList generateCardList() {
        if (this.cardList.size() == 1 ){
            return this.cardList;
        }
        else {
            cardlistshow = new CardList();
            cardList.sort();

            int rotation = 1 ;//intValue(this.cardList.size() / 10);
            int rotationindex = 0;
            while (this.cardList.size() > 0) {

                cardlistshow.add(this.cardList.popCard(1/*intValue(rotationindex * this.cardList.size() / rotation)*/));

            }
            return cardlistshow;
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    //distributeurs de card intelligent
    public Card generateCard() {
        //System.out.println(this.cardList.pop().getName());

        if (this.cardList.endCardList() ){
            //System.out.println("fin du deck");
            this.cardList = this.cardList.generateCardListNotLearn();
            return this.cardList.pop(0);
        }

        else {
            Card card = this.cardList.pop(0);
            return card;
        }

        //return cardList.pop().getName();

    }





}
