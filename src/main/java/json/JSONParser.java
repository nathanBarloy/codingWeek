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
    /*
    public Card JsonToCard(String pathname){
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(s);
            JSONArray array = (JSONArray)obj;
        Card card = new Card();

        return card
    }*/

}
