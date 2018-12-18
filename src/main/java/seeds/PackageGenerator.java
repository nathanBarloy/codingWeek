package seeds;

import models.Card;
import models.CardStack;
import models.Player;

public class PackageGenerator {
    private Player player;
    private CardStack cardStack;

    public PackageGenerator() {

        player=new Player("Olivier", "azerty");
        cardStack=new CardStack("test", "test", player);
        CardStackSeed cardStackSeed = new CardStackSeed(cardStack,player);
    }
/*
    public CardStackPackageGenerator(int nb , int seed){
        CardStack cardstack = new CardStack("test"+seed, "description" +seed);
        for (int i =  1 ; i < nb ; i++){
            cardstack.push();

        }
    }*/
}

