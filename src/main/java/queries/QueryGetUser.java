package queries;

import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryGetUser extends Query {


    private String username;

    public QueryGetUser(String username) {
        super("getUser");
        this.username=username;
        this.parameters+="username="+username;
    }

}
