package queries;

import models.Card;
import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryDelCard extends Query {

    private Card card;

    public QueryDelCard(Card card) {
        super("delCard");
        this.card = card;
        this.parameters+="name="+card.getName();
    }


}
