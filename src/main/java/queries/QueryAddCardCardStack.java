package queries;

import models.Card;
import models.CardList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryAddCardCardStack extends Query {

    private CardList cardList;
    private Card card;

    public QueryAddCardCardStack(Card card,CardList cardList) {
        super("addCardCardStack");
        this.cardList = cardList;
        this.card=card;
    }


    public void send() throws IOException {


// Request parameters and other properties.
            params.add(new BasicNameValuePair("cardname", card.getName()));
            params.add(new BasicNameValuePair("cardstackname", cardList.getName()));
            request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//Execute and get the response.
            HttpResponse httpResponse = httpClient.execute(request);
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                try  {
                    this.response = entity.getContent();
                }catch (Exception e){

                }
            }


    }
}
