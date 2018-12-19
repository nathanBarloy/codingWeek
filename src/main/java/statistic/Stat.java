package statistic;

import models.Card;
import models.CardList;

public class Stat {
    /*récupère les stats utilisateurs*/


    //------------------------------------------------------------------------------------------------------------------
    //Constructor
    public Stat() {
    }
    //------------------------------------------------------------------------------------------------------------------
    //Getter
    public int getStat(Card card) {
        return card.getState();
    }

    public double getProgession(CardList cardList) {
        double progression = 0 ;
        for (Card card : cardList) {
            progression = progression + this.getStat(card);
        }
        progression = progression /3 * cardList.size()*100  ;
        return progression;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Setter


}
