package queries;

import models.CardList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryAddStackCard extends Query {

    private CardList cardList;

    public QueryAddStackCard(CardList cardList) {
        super("addCardStack");
        this.cardList = cardList;
    }


    public void send() throws IOException {
        {

// Request parameters and other properties.
            params.add(new BasicNameValuePair("name", cardList.getName()));
            params.add(new BasicNameValuePair("description", cardList.getDescription()));
            params.add(new BasicNameValuePair("author", cardList.getAuthor().getUsername()));
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
}
