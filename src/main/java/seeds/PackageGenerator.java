package seeds;

import models.CardList;
import models.Player;

public class PackageGenerator {
    private Player player;
    private CardList cardList;

    public PackageGenerator() {

        player=new Player("Olivier", "azerty");
        cardList =new CardList("test", "test", player);
        CardStackSeed cardStackSeed = new CardStackSeed(cardList,player);
    }
/*
    public CardStackPackageGenerator(int nb , int seed){
        CardList cardstack = new CardList("test"+seed, "description" +seed);
        for (int i =  1 ; i < nb ; i++){
            cardstack.push();

        }
    }*/
}

