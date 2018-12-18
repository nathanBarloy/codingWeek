package queries;

import models.Card;
import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryAddCard extends Query {

    private Card card;

    public QueryAddCard(Card card) {
        super("addCard");
        this.card=card;
    }


    public void send() throws IOException {
        {

// Request parameters and other properties.
            params.add(new BasicNameValuePair("username", card.getName()));
            params.add(new BasicNameValuePair("question", card.getQuestion()));
            params.add(new BasicNameValuePair("answer", card.getAnswer()));
            params.add(new BasicNameValuePair("type", card.getType()));
            params.add(new BasicNameValuePair("author", card.getAuthor().getUsername()));
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
