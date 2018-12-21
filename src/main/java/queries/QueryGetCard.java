package queries;

import database.Database;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryGetCard extends Query {


    private String name;

    public QueryGetCard(Database db, String name) {
        super("getCard",db);
        this.name=name;
        this.parameters+="name="+name;
    }


}
