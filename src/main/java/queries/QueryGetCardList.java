package queries;

import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.IOException;

public class QueryGetCardList extends Query {

    private Player player;

    public QueryGetCardList() {
        super("getCardList");

    }


}
