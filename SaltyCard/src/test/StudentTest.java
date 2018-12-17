package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CardTest {

    private Card card;

    @Before
    public void initialiser() throws Exception {
        card = new Card();
    }

    @After
    public void nettoyer() throws Exception {
        card = null;
    }
    @Test
    public void card() {
        assertNotNull(card(););
    }