package queries;

import database.Database;
import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryDelUser extends Query {

    private Player player;

    public QueryDelUser(Database db, Player player) {
        super("delUser",db);
        this.player=player;
        try {
            this.parameters+="username="+ URLEncoder.encode(player.getUsername(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
