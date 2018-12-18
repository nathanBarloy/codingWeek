package seeds;

import models.Card;
import models.CardStack;
import models.Player;

public class CardStackSeed {

    private Player player;
    private CardStack cardStack;

    public CardStackSeed(CardStack cardStack, Player player) {
        this.player=player;
        this.cardStack = cardStack;
    }

    public void seed(){
        cardStack.add(new Card("Carte 1", "Quel est le goût du sel ?", "Salé",player));
        cardStack.add(new Card("Carte 2", "Quelle est la couleur du cheval blanc d'Henry IV ?", "Couleur sel", player));
        cardStack.add(new Card("Carte 3", "Appréciez vous la crinière dorée de ce cher Olivier ?", "Je suis salé", player));
    }

}
