package json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class JSONCardIdParser {




    /*public static String cardIdToJson(Card card) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("JSONS/file.json"), card);
        return mapper.writeValueAsString(card);
    }*/

    public static ArrayList<Integer> JsonToCardId(JSONArray jsonList){


        int n= jsonList.length();
        ArrayList<Integer> cardIds = new ArrayList<Integer>();

        for(int i=0;i<n;i++)
            cardIds.add(jsonList.getInt(i));

        return cardIds;
    }

    public static ArrayList<Integer> JsonToCardId(String jsonCardId){
        return JsonToCardId(new JSONArray(jsonCardId));
    }





}
