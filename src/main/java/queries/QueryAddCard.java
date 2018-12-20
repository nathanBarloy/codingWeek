package queries;

import models.Card;
import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class QueryAddCard extends Query {

    private Card card;

    public QueryAddCard(Card card) {
        super("addCard");
        this.card=card;
        parameters+="name="+card.getName()+"&question="+card.getQuestion()+"&answer="+"&type="+card.getType()+"&author="+card.getAuthor();

    }



}
