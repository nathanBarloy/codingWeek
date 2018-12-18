package seeds;

import models.Card;
import models.CardList;
import models.Player;

public class CardStackSeed {

    private Player player;
    private CardList cardList;

    public CardStackSeed(CardList cardList, Player player) {
        this.player=player;
        this.cardList = cardList;
    }

    public void seed(){
        cardList.add(new Card("Carte 1", "Quel est le goût du sel ?", "Salé",player));
        cardList.add(new Card("Carte 2", "Quelle est la couleur du cheval blanc d'Henry IV ?", "Couleur sel", player));
        cardList.add(new Card("Carte 3", "Appréciez vous la crinière dorée de ce cher Olivier ?", "Je suis salé", player));
    }

}
