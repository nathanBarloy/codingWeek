package queries;

import models.CardList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryDelCardStack extends Query {

    private CardList cardStack;

    public QueryDelCardStack(CardList cardStack) {
        super("delCardStack");
        this.cardStack = cardStack;
    }


    public void send() throws IOException {


// Request parameters and other properties.
            params.add(new BasicNameValuePair("name", cardStack.getName()));
            request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//Execute and get the response.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                try  {
                    this.response = entity.getContent();
                    System.out.println(response.toString());
                }catch (Exception e){

                }
            }


    }
}