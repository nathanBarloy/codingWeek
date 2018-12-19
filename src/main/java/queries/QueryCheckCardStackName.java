package queries;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class QueryCheckCardStackName extends Query {

    private String cardstackname;

    public QueryCheckCardStackName(String cardstackname) {
        super("checkCardStackName");
        this.cardstackname=cardstackname;

    }


    public void send() throws IOException {


// Request parameters and other properties.
        params.add(new BasicNameValuePair("cardstackname", cardstackname ));
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