package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Card;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.File;
import java.io.IOException;


public class JSONParser {
    private JSONObject json;



    public String cardToJson(Card card) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("JSONS/file.json"), card);
        return mapper.writeValueAsString(card);
    }

    public Card JsonToCard(String jsonCard){
        json = new JSONObject(jsonCard);

        String name=json.getString("name");
        String question=json.getString("question");
        String answer=json.getString("answer");
        String author=json.getString("author");
        String type=json.getString("type");
        return new Card(name,question,answer,author);
    }

    public Card[] JsonToCardList(String jsonCardList){

        String[] jsonList=jsonCardList.replace("}{","}\n{").split("\n");
        int n= jsonList.length;
        Card[] cardList= new Card[n];
        for(int i=0;i<n;i++) {
            
            cardList[i] = JsonToCard(jsonList[i]);
        }
        return cardList;
    }

}
