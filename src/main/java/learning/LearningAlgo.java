package learning;

import models.Card;
import models.CardList;

import java.util.ArrayList;

import static oracle.jrockit.jfr.events.Bits.intValue;

public class LearningAlgo {
    /*gère la selection des cartes à afficher*/
    private CardList cardList;
    private CardList cardlistshow;

    public LearningAlgo (CardList cardList ) {

        this.cardList = new CardList(cardList);
    }

    public  CardList generateCardList() {
        if (this.cardList.size() == 1 ){
            return this.cardList;
        }
        else {
            cardlistshow = new CardList();
            cardList.sort();

            int rotation = intValue(this.cardList.size() / 10);
            int rotationindex = 0;
            while (this.cardList.size() > 0) {

                cardlistshow.add(this.cardList.popCard(intValue(rotationindex * this.cardList.size() / rotation)));

            }
            return cardlistshow;
        }
    }
   /*
    public Card getCard(){
        System.out.println("iici" +cardstack.getNbCards());
        return this.cardstack.getCard();
*/


}
