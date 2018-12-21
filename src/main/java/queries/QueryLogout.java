package queries;

import database.Database;
import models.Player;

public class QueryLogout extends Query {

    private Player player;

    public QueryLogout(Database db) {
        super("logout",db);

    }


}
