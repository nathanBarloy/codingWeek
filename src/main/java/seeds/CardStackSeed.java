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


    public CardStackSeed(CardList cardList) {
        this.cardList = cardList;
    }

    public void seed(String nom){
        cardList.add(new Card("Carte 1" + nom, "Quel est le goût du sel ?", "Salé",player));
        cardList.add(new Card("Carte 2" + nom, "Quelle est la couleur du cheval blanc d'Henry IV ?", "Couleur sel", player));
        cardList.add(new Card("Carte 3" + nom, "Appréciez vous la crinière dorée de ce cher Olivier ?", "Je suis salé", player));
    }

    public void seed(){
        cardList.add(new Card("Carte 1" + "default", "Quel est le goût du sel ?", "Salé",player));
        cardList.add(new Card("Carte 2" + "default", "Quelle est la couleur du cheval blanc d'Henry IV ?", "Couleur sel", player));
        cardList.add(new Card("Carte 3" + "default", "Appréciez vous la crinière dorée de ce cher Olivier ?", "Je suis salé", player));
    }

}
