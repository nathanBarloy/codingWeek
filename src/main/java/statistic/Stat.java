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
        if (cardList.size()>0) {
            for (Card card : cardList) {
                //System.out.println(card.getName() +": stat = " +  this.getStat(card));
                //if (this.getStat(card) > 3){
                  //  progression ++;
                //}
                progression = progression + this.getStat(card);

            }
        }
        progression = progression/(cardList.size()*4)  ;

        return progression;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Setter


}
