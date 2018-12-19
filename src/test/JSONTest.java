

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.*;
import json.*;
import queries.*;


import java.io.IOException;


public class JSONTest {
    private Player player;
    private Card[] cards;
    private Player[] users;
    private CardList[] cardStacks;

    private Query query;

    @Before
    public void initialiser() throws Exception {
        player = new Player("Alexis","az");



    }

    @After
    public void nettoyer() throws Exception {
        cards = null;

    }

    @Test
    public void card() throws IOException {
        query = new QueryGetCardList();
        query.send();

        cards= JSONCardParser.JsonToCardList(query.getResponse());
        for(Card c:cards)
            System.out.println(c.toString());

        query = new QueryGetUserList();
        query.send();

        users= JSONUserParser.JsonToUserList(query.getResponse());
        for(Player u:users)
            System.out.println(u.toString());

        query = new QueryGetCardStackList();
        query.send();

        cardStacks= JSONCardStackParser.JsonToCardStackList(query.getResponse());
        for(CardList c:cardStacks)
            System.out.println(c.toString());

    }
}