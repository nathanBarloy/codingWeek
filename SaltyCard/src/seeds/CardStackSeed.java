package seeds;

import models.Card;
import models.CardStack;

public class CardStackSeed {

    private CardStack cardStack;

    public CardStackSeed(CardStack cardStack) {
        this.cardStack = cardStack;
    }

    public void seed(){
        cardStack.push(new Card("Carte 1", "Quel est le goût du sel ?", "Salé"));
        cardStack.push(new Card("Carte 2", "Quelle est la couleur du cheval blanc d'Henry IV ?", "Couleur sel"));
        cardStack.push(new Card("Carte 3", "Appréciez vous la crinière dorée de ce cher Olivier ?", "Je suis salé"));
    }

}
