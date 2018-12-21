package queries;

import database.Database;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryCheckCardStackName extends Query {

    private String cardstackname;

    public QueryCheckCardStackName( Database db,String cardstackname) {
        super("checkCardStackName",db);
        this.cardstackname=cardstackname;
        this.parameters+="cardstackname="+cardstackname;
    }


}
