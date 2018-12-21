package queries;

import database.Database;
import models.CardList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryDelCardStack extends Query {

    private CardList cardStack;

    public QueryDelCardStack(Database db, CardList cardStack) {
        super("delCardStack",db);
        this.cardStack = cardStack;
        try {
            this.parameters+="name="+ URLEncoder.encode(cardStack.getName(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
