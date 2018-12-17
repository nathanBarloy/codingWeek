package src.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import SaltyCard.src.main.java.models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models;




public class CardTest {

    private Card card;

    @Before
    public void initialiser() throws Exception {
        card = new Card("la vérité blesse", "Qui est le plus salé du groupe? ", "Alexis<3");
    }

    @After
    public void nettoyer() throws Exception {
        card = null;
    }

    @Test
    public void card() {
        assertNotNull("card pas créée", card);
        assertEquals("name ", card.getName(), "la vérité blesse");

    }
}