package queries;

import models.Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;

public class QueryAddUser extends Query {

    private Player player;

    public QueryAddUser(Player player) {
        super("addUser");
        this.player=player;
    }


    public void send() throws IOException {


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
