package queries;

import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryAddStackCard extends Query {

    Player player;

    public QueryAddStackCard(String action, Player player) {
        super(action);
        this.player=player;
    }


    public void send() throws IOException {
        {

// Request parameters and other properties.
            params.add(new BasicNameValuePair("username", player.getUsername()));
            params.add(new BasicNameValuePair("description", player.getDescription()));
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
