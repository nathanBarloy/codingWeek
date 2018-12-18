

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.*;



public class CardTest {

    private Card card;
    private Player player;
    @Before
    public void initialiser() throws Exception {
        player = new Player("Alexis","az");
        card = new Card("la vérité blesse", "Qui est le plus salé du groupe? ", "Alexis<3", player);
    }

    @After
    public void nettoyer() throws Exception {
        card = null;
        player=null;
    }

    @Test
    public void card() {

        assertNotNull("card pas créée", card);
        assertEquals("name ", card.getName(), "la vérité blesse");

    }

}