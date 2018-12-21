package queries;

import database.Database;
import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryAddUser extends Query {


    public QueryAddUser( Database db,String player, String password) {
        super("addUser",db);
        try {
            this.parameters+="username="+ URLEncoder.encode(player,"UTF-8")+"&password="+URLEncoder.encode(password,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
