package queries;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryCheckCardStackName extends Query {

    private String cardstackname;

    public QueryCheckCardStackName(String cardstackname) {
        super("checkCardStackName");
        this.cardstackname=cardstackname;
        this.parameters+="cardstackname="+cardstackname;
    }


}
