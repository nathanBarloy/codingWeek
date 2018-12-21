package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.Database;
import models.Card;
import models.CardList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import models.CardList;

public class JSONListCardListParser {

    public static String ListCardListToJson(ArrayList<CardList> listCardList ) throws IOException {
        System.out.println("ici");
        JSONArray jsonlistCardList = new JSONArray();
        for (CardList cardList : listCardList){
            JSONObject jsoncardList = new JSONObject();
            jsoncardList.put("author" ,  cardList.getAuthor());
            jsoncardList.put("name" ,  cardList.getName());
            jsoncardList.put("description" ,  cardList.getDescription());
            jsoncardList.put("len" ,  cardList.getNbCards());
            //creation de la carte stack
            JSONArray jsoncardStack = new JSONArray();

            for(Card card : cardList.getCardStack()){
                JSONObject jsoncard = new JSONObject();
                jsoncard.put("name" ,  card.getName());
                jsoncard.put("text" ,  card.getText());
                jsoncard.put("answer" ,  card.getAnswer());
                jsoncard.put("type" ,  card.getType());
                jsoncard.put("user_succes" ,  card.getUser_succes());
                jsoncard.put("fail" ,  card.getFail());
                jsoncard.put("succes" ,  card.getSucces());
                //ajout de la carte à la stack
                jsoncardStack.put(jsoncard);
            }
            jsoncardList.put("cardStack" , jsoncardStack);
            //ajout du deck
            jsonlistCardList.put(jsoncardList);
            }


        return jsonlistCardList.toString();
        /*
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("JSONS/listCardList.json"), listCardList);
        return mapper.writeValueAsString(listCardList);
        */
    }

    public static ArrayList<CardList> JsonToListCardList(String jsonListCardList) throws IOException{

        ArrayList<CardList> listArrayList = new ArrayList<CardList>();

        JSONArray jsonArrayList= new JSONArray(jsonListCardList);
        int n= jsonArrayList.length();

        for(int i=0;i<n;i++) {

            JSONObject jsonCardlist = jsonArrayList.getJSONObject(i);
            CardList cardList = new CardList();

            cardList.setName(jsonCardlist.getString("name"));
            cardList.setAuthor(jsonCardlist.getString("author"));
            cardList.setDescription(jsonCardlist.getString("description"));
            cardList.setLen(jsonCardlist.getInt("len"));

            JSONArray jsonCardStack = jsonCardlist.getJSONArray("cardStack");
            int m= jsonCardStack.length();

            for(int j=0;j<m;j++) {
                String name = jsonCardStack.getJSONObject(j).getString("name");
                String text = jsonCardStack.getJSONObject(j).getString("text");
                String answer = jsonCardStack.getJSONObject(j).getString("answer");
                String type = jsonCardStack.getJSONObject(j).getString("type");
                int user_succes = jsonCardStack.getJSONObject(j).getInt("user_succes");
                int fail = jsonCardStack.getJSONObject(j).getInt("fail");
                int succes = jsonCardStack.getJSONObject(j).getInt("succes");
                Card card = new Card ( name, text,  answer,  type , user_succes , fail , succes);
                cardList.getCardStack().add(card);

            }


            listArrayList.add(cardList);
        }
        return listArrayList;
    }





}
