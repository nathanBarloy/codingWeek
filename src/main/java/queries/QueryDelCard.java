package queries;

import database.Database;
import models.Card;
import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryDelCard extends Query {



    public QueryDelCard(Database db, Card card) {
        super("delCard",db);
        this.parameters+="name="+card.getName();
    }


}
