package seeds;

import models.Card;
import models.CardStack;

public class PackageGenerator {
    Card card1 = new Card("la vérité blesse", "Qui est le plus salé du groupe? ", "Alexis<3");
    Card card2 = new Card("sql", "Qui est le génie du sql? ", "Alexis");
    Card card3 = new Card("temps", "Quelle heure est-il? ", "L'heure d'une petite pause");
    CardStack cardstack1 = new CardStack("test1", "pour test ");

    public PackageGenerator() {
    }
/*
    public CardStackPackageGenerator(int nb , int seed){
        CardStack cardstack = new CardStack("test"+seed, "description" +seed);
        for (int i =  1 ; i < nb ; i++){
            cardstack.push();

        }
    }*/
}

