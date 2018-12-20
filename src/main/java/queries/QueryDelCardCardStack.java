package queries;

import models.Card;
import models.CardList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryDelCardCardStack extends Query {

    private CardList cardList;
    private Card card;

    public QueryDelCardCardStack(Card card, CardList cardList) {
        super("delCardCardStack");
        this.cardList = cardList;
        this.card=card;
        this.parameters+="cardname="+card.getName()+"&cardstackname="+cardList.getName();
    }


}
