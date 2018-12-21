package queries;

import database.Database;
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
import javax.net.ssl.SSLException;
import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public abstract class Query {

    protected String action;
    protected String server;

    protected String parameters;
    protected String token;
    protected HttpsURLConnection httpsURLConnection;
    protected URL url;
    protected StringBuffer response;
    protected Database db;
    private String stringResponse;

    public Query(String action, Database db){
        this.db=db;

        token="";
        stringResponse="-1";
        this.action=action;
        this.server= "https://saltycard.elmrini.fr/index.php?action=";
        this.response = new StringBuffer();
        try {
            url=new URL(this.server+this.action);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
        } catch (MalformedURLException e) {

            System.out.println("Error malformedurl: unable to connect");
            e.printStackTrace();
        } catch (IOException e) {

            System.out.println("Error IO: unable to connect");
            e.printStackTrace();
        }
        try {
            httpsURLConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {

            e.printStackTrace();
        }
        setToken(db.getSessionToken());
        parameters = "";
    }

    public String getToken() {
        return token;
    }

    public Query(String action, Database db, String token){
        this(action,db);
        setToken(token);
    }

    public void setToken(String token){
        if(!("".equals(token)))
            httpsURLConnection.setRequestProperty("Cookie", "PHPSESSID="+token+";");
    }

    public void makeCookie(){

        Map<String, List<String>> headerFields = httpsURLConnection.getHeaderFields();
        Set<String> headerFieldsSet = headerFields.keySet();
        Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
        while (hearerFieldsIter.hasNext()) {
            String headerFieldKey = hearerFieldsIter.next();
            if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {
                List<String> headerFieldValue = headerFields.get(headerFieldKey);
                for (String headerValue : headerFieldValue) {

                    String[] fields = headerValue.split(";\\s*");
                    String cookieValue = fields[0];
                    String expires = null;
                    String path = null;
                    String domain = null;
                    boolean secure = false;
                    for (int j = 1; j < fields.length; j++) {
                        if ("secure".equalsIgnoreCase(fields[j])) {
                            secure = true;
                        }else if (fields[j].indexOf('=') > 0) {
                            String[] f = fields[j].split("=");
                            if ("expires".equalsIgnoreCase(f[0])) {
                                expires = f[1];
                            }
                                else if ("domain".equalsIgnoreCase(f[0])) {
                                domain = f[1];
                            }
	                            else if ("path".equalsIgnoreCase(f[0])) {
                                path = f[1];
                            }
                        }
                    }
                    String[] cookie= cookieValue.split("=");
                    if(cookie[0].equals("PHPSESSID")){
                        String cookieJoined[]= new String[cookie.length-1];
                        for(int i=0;i<cookie.length-1;i++)
                            cookieJoined[i]=cookie[i+1];

                        token=String.join("=",cookieJoined);
                        db.setSessionToken(token);
                        System.out.println("cookie : " + token);


                    }
                }


            }

        }


    }

    public void send() {
        httpsURLConnection.setDoOutput(true);
        try {
            System.out.println("token : " + db.getSessionToken());
            DataOutputStream wr = new DataOutputStream(httpsURLConnection.getOutputStream());

            wr.writeBytes(parameters);

            wr.flush();
            wr.close();
            makeResponse();
            makeCookie();

        }catch (UnknownHostException e){

            System.out.println("Erreur : impossible de se connecter");
        } catch (SSLException e){

            System.out.println("Erreur de connexion SSL");

        } catch (IOException e) {

            System.out.println("Erreur de connexion IO");
            e.printStackTrace();
        }

    }

    public String getResponse() {
        return stringResponse;
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
            stringResponse=response.toString();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }



}
