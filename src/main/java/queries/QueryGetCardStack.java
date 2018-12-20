package queries;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryGetCardStack extends Query {


    private String name;

    public QueryGetCardStack(String name) {
        super("getCardStack");
        this.name=name;
        this.parameters+="name="+name;
    }


}
