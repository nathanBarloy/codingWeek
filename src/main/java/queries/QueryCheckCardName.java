package queries;

import database.Database;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryCheckCardName extends Query {

    private String cardname;

    public QueryCheckCardName(String cardname, Database db) {
        super("checkCardName",db);
        this.cardname=cardname;
        this.parameters+="cardname="+cardname;
    }


}
