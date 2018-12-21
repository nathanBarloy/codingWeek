package queries;

import database.Database;
import models.Card;
import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.URLEncoder;

public class QueryAddCard extends Query {

    private Card card;

    public QueryAddCard( Database db,Card card) {
        super("addCard",db);
        this.card=card;
        try {
            parameters+="name="+ URLEncoder.encode(card.getName(),"UTF-8")+"&question="+URLEncoder.encode(card.getQuestion(),"UTF-8")+"&answer="+URLEncoder.encode(card.getAnswer(),"UTF-8")+"&type="+card.getType()+"&author="+URLEncoder.encode(card.getAuthor(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }



}
