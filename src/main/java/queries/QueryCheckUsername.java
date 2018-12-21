package queries;

import database.Database;
import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryCheckUsername extends Query {

    private String player;

    public QueryCheckUsername(Database db, String player) {
        super("checkUsername",db);
        this.player=player;
        this.parameters+="username="+player;
    }



}
