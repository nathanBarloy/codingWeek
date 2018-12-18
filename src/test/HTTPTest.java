

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.*;
import json.*;
import queries.Query;
import queries.QueryAddUser;

import java.io.IOException;


public class HTTPTest {

    private Player player;
    private QueryAddUser query;


    @Before
    public void initialiser() throws Exception {
        player = new Player("Olivier","Le plus sexy");
        query = new QueryAddUser(player);

    }

    @After
    public void nettoyer() throws Exception {
        player = null;
        query = null;
    }

    @Test
    public void card() throws IOException {
        query.send();
    }
}