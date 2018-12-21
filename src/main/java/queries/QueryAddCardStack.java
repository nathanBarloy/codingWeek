package queries;

import database.Database;
import models.CardList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryAddCardStack extends Query {

    private CardList cardList;

    public QueryAddCardStack( Database db,CardList cardList) {
        super("addCardStack",db);
        this.cardList = cardList;
        this.parameters+="name="+cardList.getName()+"&description="+cardList.getDescription()+"&author="+cardList.getAuthor();
    }


}
