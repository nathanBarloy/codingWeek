package queries;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class Query {
    protected String action;
    protected String server;
    protected DefaultHttpClient httpClient;

    protected HttpPost request;
    protected List<NameValuePair> params;
    protected InputStream response;


    public Query(String action){
        this.action=action;
        this.server= "http://saltycard.elmrini.fr/index.php?action=";
        httpClient = new DefaultHttpClient();
        request = new HttpPost(this.server+this.action);
        params = new ArrayList<NameValuePair>();

    }



    public abstract void send() throws IOException;

    public String getResponse() {
        StringWriter writer = new StringWriter();
        try{
            IOUtils.copy(response, writer, "UTF-8");
        }catch(IOException e){
            System.out.println("Unable to create a String response from the HTTP response");
        }

        return writer.toString().replaceAll("^\\s+","").replaceAll("\\s+$","");
    }

}
