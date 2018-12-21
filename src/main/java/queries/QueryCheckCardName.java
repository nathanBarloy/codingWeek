package queries;

import database.Database;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryCheckCardName extends Query {

    private String cardname;

    public QueryCheckCardName(String cardname, Database db)  {
        super("checkCardName",db);
        this.cardname=cardname;
        try {
            this.parameters+="cardname="+ URLEncoder.encode(cardname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
