

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import database.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.*;
import seeds.CardStackSeed;


public class CardListTest {

    private CardList cardList;

    @Before
    public void initialiser() throws Exception {
        this.cardList = new CardList();
        CardStackSeed cardStackSeed = new CardStackSeed(this.cardList);
        cardStackSeed.seed();
    }

    @After
    public void nettoyer() throws Exception {
    }

    @Test
    public void card() {

        assertNotNull("card pas créée", cardList);
        assertEquals("carte1 ", "Carte 1", cardList.getCard().getName());
        assertEquals("fin1 ", false, cardList.endCardList());
        assertEquals("carte2 ", "Carte 2" , cardList.getnextCard().getName());
        assertEquals("fin2 ", false, cardList.endCardList());
        assertEquals("carte3", "Carte 3", cardList.getnextCard().getName());
        assertEquals("fin3 ", true, cardList.endCardList());


    }

}