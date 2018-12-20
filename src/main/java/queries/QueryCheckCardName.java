package queries;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryCheckCardName extends Query {

    private String cardname;

    public QueryCheckCardName(String cardname) {
        super("checkCardName");
        this.cardname=cardname;
        this.parameters+="cardname="+cardname;
    }


}
