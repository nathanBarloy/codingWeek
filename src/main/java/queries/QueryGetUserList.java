package queries;

import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryGetUserList extends Query {

    private Player player;

    public QueryGetUserList() {
        super("getUserList");

    }


    public void send() throws IOException {


// Request parameters and other properties.

//Execute and get the response.
            HttpResponse httpResponse = httpClient.execute(request);
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                try  {
                    this.response = entity.getContent();
                    System.out.println(getResponse());
                }catch (Exception e){

                }
            }


    }
}
