package queries;

import database.Database;
import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.IOException;

public class QueryGetCardStackList extends Query {

    private Player player;

    public QueryGetCardStackList(Database db) {
        super("getCardStackList",db);

    }

}
