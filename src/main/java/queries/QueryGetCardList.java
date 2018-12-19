package queries;

import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.IOException;

public class QueryGetCardList extends Query {

    private Player player;

    public QueryGetCardList() {
        super("getCardList");

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
