package statistic;

import models.Card;

public class Stat {
    /*récupère les stats utilisateurs*/
    private int progession;

    public Stat() {
    }

    public int getStat(Card card ) {
        return card.getUser_succes();
    }
}
