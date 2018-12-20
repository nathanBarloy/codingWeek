package queries;

import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryGetCardListUser extends Query {

    private String player;

    public QueryGetCardListUser(String player) {
        super("getCardListUser");
        this.player=player;
        this.parameters+="username="+player;
    }


}
