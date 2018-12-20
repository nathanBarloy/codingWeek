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
        double progression = 0.0 ;
        for (Card card : cardList) {
            System.out.println(card.getName()+ this.getStat(card));
            progression = progression + this.getStat(card);

        }
        System.out.println(progression);
        progression = (progression /3) / cardList.size()  ;

        return progression;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Setter


}
