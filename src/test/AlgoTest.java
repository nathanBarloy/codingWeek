import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import learning.LearningAlgo;
import models.Card;
import models.CardStack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AlgoTest {
    private LearningAlgo learningAlgo;
    Card card1;
    Card card2;
    Card card3;

    @Before
    public void initialiser() throws Exception {
        card1 = new Card("la vérité blesse", "Qui est le plus salé du groupe? ", "Alexis<3");
        card2 = new Card("sql", "Qui est le génie du sql? ", "Alexis" );
        card3 = new Card("temps", "Quelle heure est-il? ", "L'heure d'une petite pause" );
        CardStack cardstack = new CardStack( "test" , "pour test ");
        cardstack.push(card1);
        cardstack.push(card2);
        cardstack.push(card3);
         learningAlgo = new LearningAlgo(cardstack);

    }

    @After
    public void nettoyer() throws Exception {
    }

    @Test
    public void learningAlgo() {

        assertNotNull("card pas créée", learningAlgo);
        assertEquals("permièrecarte", learningAlgo.getCard(), card3);
        assertEquals("deuxièmecarte", learningAlgo.getCard(), card2);
        assertEquals("troisièmecarte", learningAlgo.getCard(), card1);

    }
}
