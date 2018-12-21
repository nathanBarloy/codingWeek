package queries;

import database.Database;
import models.Card;
import models.CardList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryAddCardCardStack extends Query {

    private CardList cardList;
    private Card card;

    public QueryAddCardCardStack( Database db, Card card, CardList cardList) {
        super("addCardCardStack",db);
        this.cardList = cardList;
        this.card=card;
        try {
            parameters+="cardname="+ URLEncoder.encode(card.getName(),"UTF-8")+"&cardstackname="+URLEncoder.encode(cardList.getName(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
