import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import learning.LearningAlgo;
import models.Card;
import models.CardList;
import models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AlgoTest {
    private LearningAlgo learningAlgo;
    Card card1;
    Card card2;
    Card card3;
    Player player;
    @Before
    public void initialiser() throws Exception {
        player=new Player("Olivier","L'homme du sel");
        card1 = new Card("la vérité blesse", "Qui est le plus salé du groupe? ", "Alexis<3", player);
        card2 = new Card("sql", "Qui est le génie du sql? ", "Alexis" , player);
        card3 = new Card("temps", "Quelle heure est-il? ", "L'heure d'une petite pause", player );
        CardList cardstack = new CardList( "test" , "pour test ", player);
        cardstack.add(card1);
        cardstack.add(card2);
        cardstack.add(card3);
        learningAlgo = new LearningAlgo(cardstack);
        //System.out.println(cardstack.pop().getName());
        //System.out.println(card1.getName());
        //System.out.println(cardstack.pop().getName());


        //System.out.println(learningAlgo.getCard().getName());



    }

    @After
    public void nettoyer() throws Exception {
    }

    @Test
    public void learningAlgo() {

        assertNotNull("card pas créée", learningAlgo);
        assertEquals("permièrecarte" , card1 ,learningAlgo.generateCard());
        assertEquals("deuxièmecarte" ,  card2 ,learningAlgo.generateCard());
        assertEquals("troisièmecarte" , card3 ,learningAlgo.generateCard());
        //assertEquals("permièrecarte2" , card1 ,learningAlgo.generateCard());


    }
}
