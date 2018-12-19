package queries;

import models.CardList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryAddCardCardStack extends Query {

    private CardList cardList;

    public QueryAddCardCardStack(CardList cardList) {
        super("addCardCardStack");
        this.cardList = cardList;
    }


    public void send() throws IOException {


// Request parameters and other properties.
            params.add(new BasicNameValuePair("cardname", cardList.getName()));
            params.add(new BasicNameValuePair("description", cardList.getDescription()));
            params.add(new BasicNameValuePair("author", cardList.getAuthor().getUsername()));
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
