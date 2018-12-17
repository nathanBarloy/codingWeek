package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Card;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;


public class JSONParser {
    private JSONObject json;

    public Card jsonToCard(JSONObject json){
        return new Card(json.getString("name"),json.getString("question"),json.getString("answer"));
    }

    public String cardToJson(Card card) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("JSONS/file.json"), card);
        return mapper.writeValueAsString(card);
    }

}
