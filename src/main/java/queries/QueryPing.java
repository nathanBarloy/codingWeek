package queries;

import database.Database;
import models.Card;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryPing extends Query {


    public QueryPing(Database db) {
        super("ping",db);

    }

}
