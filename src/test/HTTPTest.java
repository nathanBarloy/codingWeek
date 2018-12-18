

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
    private CardStack cardStack1;
    private CardStack cardStack2;
    private Query query;


    @Before
    public void initialiser() throws Exception {
        player1 = new Player("Alexei","Le plus sexy");
        player2 = new Player("Vladimir","Le plus sexy");
        cardStack1= new CardStack("c1", "azerty", player1);
        cardStack2= new CardStack("c2", "azerty", player1);
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
        query = new QueryAddUser(player2);
        query.send();
        Thread.sleep(100);
        query = new QueryDelUser(player2);
        query.send();
        Thread.sleep(100);

    }

    @Test
    public void cards() throws IOException, InterruptedException {
        query = new QueryAddCard(card1);
        query.send();
        Thread.sleep(100);
        query = new QueryAddCard(card2);
        query.send();
        Thread.sleep(100);
        query = new QueryDelCard(card1);
        query.send();
        Thread.sleep(100);

    }

    @Test
    public void cardStacks() throws IOException, InterruptedException {
        query = new QueryAddCardStack(cardStack1);
        query.send();
        Thread.sleep(100);
        query = new QueryAddCardStack(cardStack2);

        query.send();
        Thread.sleep(100);
        query = new QueryDelCardStack(cardStack1);
        query.send();
        Thread.sleep(100);

    }
}