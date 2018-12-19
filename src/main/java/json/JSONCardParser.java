package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Card;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.File;
import java.io.IOException;


public class JSONCardParser {




    public static String cardToJson(Card card) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("JSONS/file.json"), card);
        return mapper.writeValueAsString(card);
    }

    public static Card JsonToCard(JSONObject jsonCard){


        String name=jsonCard.getString("name");
        String question=jsonCard.getString("question");
        String answer=jsonCard.getString("answer");

        String author=jsonCard.getString("author");
        String type=jsonCard.getString("type");
        return new Card(name,question,answer,author);
    }

    public static Card JsonToCard(String jsonCard){
        return JsonToCard(new JSONObject(jsonCard));
    }

    public static Card[] JsonToCardList(String jsonCardList){

        JSONArray jsonList= new JSONArray(jsonCardList);
        int n= jsonList.length();
        Card[] cardList= new Card[n];

        for(int i=0;i<n;i++) {

            cardList[i] = JsonToCard(jsonList.getJSONObject(i));
        }
        return cardList;
    }



}
