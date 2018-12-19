import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import database.Database;
import models.Card;
import models.CardList;
import models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {

    private Database database;
    private Database databaseresultat1;
    private Database databaseresultat2;
    private Database databaseresultat3;
    private Player player;
    @Before
    public void initialiser() throws Exception {
        player = new Player("Alexis","az");
        Card card1 = new Card("la vérité blesse", "Qui est le plus salé du groupe? ", "Alexis<3", player);
        Card card2 = new Card("sql", "Qui est le génie du sql? ", "Alexis", player);
        Card card3 = new Card("temps", "Quelle heure est-il? ", "L'heure d'une petite pause", player);
        CardList cardstack1 = new CardList("test1", "pour test1", player);
        cardstack1.add(card1);
        cardstack1.add(card2);
        cardstack1.add(card3);


        CardList cardstack2 = new CardList("test2", "pour test2",player);
        CardList cardstack3 = new CardList("test3", "pour test3", player);
        CardList cardstack4 = new CardList("test3", "pour test4", player);
        CardList cardstack5 = new CardList("test2", "pour test5", player);
        CardList cardstack6 = new CardList("test1", "pour test6",player);

        database = new Database();
        database.add(cardstack1);
        database.add(cardstack2);
        database.add(cardstack3);
        database.add(cardstack4);
        database.add(cardstack5);
        database.add(cardstack6);



    }

    @After
    public void nettoyer() throws Exception {
    }

    @Test
    public void Database() {

        assertNotNull("database pas créée", this.database);
        assertEquals("bugrecherche", database, database);
        assertEquals("bug 1er errecherche ", database.getCardList("test1").get(1).getName(), "test1");
        assertEquals("bug 1er errecherche ", database.getCardList("test1").get(1).getDescription(), "pour test6");
        assertEquals("bug 1er errecherche ", database.getCardList("test1").get(0).getDescription(), "pour test1");
       // assertEquals("bug 2eme recherche ", database.getCardList("test2"), databaseresultat2);
       // assertEquals("bug vide", database.getCardList("testpas de correpondance"), databaseresultat3);*/
    }
}
