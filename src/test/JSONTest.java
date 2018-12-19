

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
    private Card card;
    private JSONParser jsonParser;
    private Query query;

    @Before
    public void initialiser() throws Exception {
        player = new Player("Alexis","az");

        card = new Card("la vérité blesse", "Qui est le plus salé du groupe? ", "Alexis<3", player.getUsername());
        jsonParser=new JSONParser();

    }

    @After
    public void nettoyer() throws Exception {
        card = null;
        jsonParser = null;
    }

    @Test
    public void card() throws IOException {
        query = new QueryGetCard("Cheval");
        query.send();

        card=jsonParser.JsonToCard(query.getResponse());
        System.out.println(card.toString());

    }
}