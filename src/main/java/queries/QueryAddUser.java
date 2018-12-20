package queries;

import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;

public class QueryAddUser extends Query {


    public QueryAddUser(String player,String password) {
        super("addUser");
        this.parameters+="username="+player+"&password="+password;
    }


}
