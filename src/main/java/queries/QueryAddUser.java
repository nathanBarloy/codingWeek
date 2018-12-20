package queries;

import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;

public class QueryAddUser extends Query {

    private Player player;

    public QueryAddUser(Player player) {
        super("addUser");
        this.player=player;
        this.parameters+="username="+player.getUsername()+"&description="+player.getDescription();
    }


}
