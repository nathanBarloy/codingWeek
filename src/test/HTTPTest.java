

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.*;
import queries.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class HTTPTest {

    private Player player1;
    private Player player2;
    private Card card1;
    private Card card2;
    private CardList cardStack1;
    private CardList cardStack2;
    private Query query;


    @Before
    public void initialiser() throws Exception {
        player1 = new Player("Alexei","Le plus sexy");
        player2 = new Player("Vladimir","Le plus sexy");
        cardStack1= new CardList("c1", "azerty", player1);
        cardStack2= new CardList("c2", "azerty", player1);
        card1 = new Card("card1", "oui ?", "oui.",player1);
        card2 = new Card("card2", "non ?", "non.",player1);



    }

    @After
    public void nettoyer() throws Exception {
        player1 = null;
        player2 = null;
        card1 = null;
        card2 = null;
        cardStack1 = null;
        cardStack2 = null;
        query = null;
    }

    @Test
    public void users() throws IOException, InterruptedException {
        query = new QueryAddUser(player1);
        query.send();
        Thread.sleep(100);
        System.out.println("Reponse adduser 1 : "+query.getResponse());
        query = new QueryAddUser(player2);
        query.send();
        Thread.sleep(100);
        query = new QueryDelUser(player2);
        query.send();
        Thread.sleep(100);
        System.out.println("Reponse deluser 1 : "+query.getResponse());

    }

    @Test
    public void cards() throws IOException, InterruptedException {
        query = new QueryAddCard(card1);
        query.send();
        Thread.sleep(100);
        System.out.println("Reponse addcard 1 : "+query.getResponse());
        query = new QueryAddCard(card2);
        query.send();
        Thread.sleep(100);
        query = new QueryDelCard(card1);
        query.send();
        Thread.sleep(100);
        System.out.println("Reponse delcard 1 : "+query.getResponse());

    }

    @Test
    public void cardStacks() throws IOException, InterruptedException {
        query = new QueryAddCardStack(cardStack1);
        query.send();
        Thread.sleep(100);
        System.out.println("Reponse addcardstack 1 : "+query.getResponse());
        query = new QueryAddCardStack(cardStack2);

        query.send();
        Thread.sleep(100);
        query = new QueryDelCardStack(cardStack1);
        query.send();
        Thread.sleep(100);
        System.out.println("Reponse delcardstack1 : "+query.getResponse());

    }

    @Test
    public void cardCardStacks() throws IOException, InterruptedException {
        query = new QueryAddCardCardStack(card2,cardStack2);
        query.send();
        Thread.sleep(100);
        System.out.println("Reponse addcardcardstack 1 : "+query.getResponse());
        query = new QueryAddCardCardStack(card2,cardStack2);

        query.send();
        Thread.sleep(100);
        System.out.println("Reponse addcardcardstack 2 : "+query.getResponse());
        query = new QueryDelCardStack(cardStack1);
        query.send();
        Thread.sleep(100);

    }

    @Test
    public void getters() throws IOException, InterruptedException {
        query = new QueryGetUserList();
        query.send();
        Thread.sleep(100);
        System.out.println("Users :" + query.getResponse());
        query = new QueryGetCardList();
        query.send();
        Thread.sleep(100);
        System.out.println("Cards :" + query.getResponse());

        query = new QueryGetCardStackList();
        query.send();
        Thread.sleep(100);
        System.out.println("CardLists :" + query.getResponse());


        query = new QueryGetUser("SaltyMan");
        query.send();
        Thread.sleep(100);
        System.out.println("User SaltyMan :" + query.getResponse());

        query = new QueryGetCard("Cheval");
        query.send();
        Thread.sleep(100);
        System.out.println("Card Cheval :" + query.getResponse());

        query = new QueryGetCardStack("SaltyStack");
        query.send();
        Thread.sleep(100);
        System.out.println("Cardlist SaltyStack :" + query.getResponse());

    }

    @Test
    public void checks() throws IOException, InterruptedException {
        query = new QueryCheckUsername("Amos");
        query.send();
        Thread.sleep(100);
        System.out.println("Amos existe-t-il ? " + query.getResponse());
        //assertTrue(query.getResponse().contains("1"));
        query = new QueryCheckUsername("toi");
        query.send();
        Thread.sleep(100);
        System.out.println("Toi existe-t-il ? " + query.getResponse());
        //assertTrue(query.getResponse().contains("0"));
        query = new QueryCheckCardName("politesse");
        query.send();
        Thread.sleep(100);
        System.out.println("La carte politesse existe-t-elle ? " + query.getResponse());
        //assertTrue(query.getResponse().contains("1"));
        query = new QueryCheckCardName("impolitesse");
        query.send();
        Thread.sleep(100);
        System.out.println("La carte impolitesse existe-t-elle ? " + query.getResponse());
        //assertTrue(query.getResponse().contains("0"));
        query = new QueryCheckCardStackName("SaltyStack");
        query.send();
        Thread.sleep(100);
        System.out.println("La cardstack SaltyStack existe-t-elle ? " + query.getResponse());
        //assertTrue(query.getResponse().contains("1"));
        query = new QueryCheckUsername("impolitesse");
        query.send();
        Thread.sleep(100);
        System.out.println("La cardstack impolitesse existe-t-elle ? " + query.getResponse());
        //assertTrue(query.getResponse().contains("0"));
    }
}