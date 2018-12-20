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


import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class Query {
    protected String action;
    protected String server;

    protected String parameters;

    protected HttpsURLConnection httpsURLConnection;
    protected URL url;
    protected StringBuffer response;

    public Query(String action){

        this.action=action;
        this.server= "https://saltycard.elmrini.fr/index.php?action=";
        this.response = new StringBuffer();
        try {
            url=new URL(this.server+this.action);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpsURLConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        parameters = "";
    }

    public Query(String action, String token){
        this(action);
        parameters="sessid="+token+"&";
    }


    public void send() {
        httpsURLConnection.setDoOutput(true);
        try {
        DataOutputStream wr = new DataOutputStream(httpsURLConnection.getOutputStream());

        wr.writeBytes(parameters);

        wr.flush();
        wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        makeResponse();

    }

    public String getResponse() {


        return response.toString();
    }

    private void makeResponse(){
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(httpsURLConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
