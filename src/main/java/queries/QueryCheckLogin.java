package queries;

import database.Database;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryCheckLogin extends Query {


    public QueryCheckLogin(Database db, String player, String password) {
        super("checkLogin",db);
        try {
            this.parameters+="username="+ URLEncoder.encode(player,"UTF-8")+"&password="+URLEncoder.encode(password,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



}
