package queries;

import database.Database;
import models.CardList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryAddCardStack extends Query {

    private CardList cardList;

    public QueryAddCardStack( Database db,CardList cardList) {
        super("addCardStack",db);
        this.cardList = cardList;
        try {
            this.parameters+="name="+ URLEncoder.encode(cardList.getName(),"UTF-8")+"&description="+URLEncoder.encode(cardList.getDescription(),"UTF-8")+"&author="+URLEncoder.encode(cardList.getAuthor(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
