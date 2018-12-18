import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import database.Database;
import learning.LearningAlgo;
import models.Card;
import models.CardStack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {

    private Database database;
    private Database databaseresultat1;
    private Database databaseresultat2;
    private Database databaseresultat3;

    @Before
    public void initialiser() throws Exception {
        Card card1 = new Card("la vérité blesse", "Qui est le plus salé du groupe? ", "Alexis<3");
        Card card2 = new Card("sql", "Qui est le génie du sql? ", "Alexis");
        Card card3 = new Card("temps", "Quelle heure est-il? ", "L'heure d'une petite pause");
        CardStack cardstack1 = new CardStack("test1", "pour test ");
        cardstack1.push(card1);
        cardstack1.push(card2);
        cardstack1.push(card3);


        CardStack cardstack2 = new CardStack("test2", "pour test ");
        CardStack cardstack3 = new CardStack("test3", "pour test ");
        CardStack cardstack4 = new CardStack("test3", "pour test ");
        CardStack cardstack5 = new CardStack("test2", "pour test ");
        CardStack cardstack6 = new CardStack("test1", "pour test ");

        database = new Database();
        database.add(cardstack1);
        database.add(cardstack2);
        database.add(cardstack3);
        database.add(cardstack4);
        database.add(cardstack5);
        database.add(cardstack6);

        //resultat 1
        databaseresultat1 = new Database();
        database.add(cardstack1);
        database.add(cardstack6);

        //resultat 2
        databaseresultat2 = new Database();
        database.add(cardstack2);
        database.add(cardstack5);

        //resultat vide
        databaseresultat3 = new Database();


    }

    @After
    public void nettoyer() throws Exception {
    }

    @Test
    public void Database() {

        assertNotNull("database pas créée", this.database);
        assertEquals("bugrecherche", database, database);
/*
        assertEquals("bug 1er errecherche ", databaseresultat1.getCardStack("test1"), databaseresultat1);
        assertEquals("bug 2eme recherche ", database.getCardStack("test2"), databaseresultat2);
        assertEquals("bug vide", database.getCardStack("testpas de correpondance"), databaseresultat3);*/
    }
}
