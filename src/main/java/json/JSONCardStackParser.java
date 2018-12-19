package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Card;
import models.CardList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;




public class JSONCardStackParser {




    public static String cardStackToJson(CardList cardStack) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("JSONS/CardStack"+cardStack.getName()+".json"), cardStack);
        return mapper.writeValueAsString(cardStack);
    }

    public static CardList JsonToCardStack(JSONObject jsonCardStack){
        String name=jsonCardStack.getString("name");
        String description=jsonCardStack.getString("description");
        ArrayList<Integer> cardIds = JSONCardIdParser.JsonToCardId(jsonCardStack.getJSONArray("cardIds"));
        String author=jsonCardStack.getString("author");
        CardList cl = new CardList(name,description,author);
        cl.setCardIds(cardIds);
        return cl;
    }

    public static CardList JsonToCard(String jsonCardStack){
        return JsonToCardStack(new JSONObject(jsonCardStack));
    }

    public static CardList[] JsonToCardStackList(String jsonCardStackList){

        JSONArray jsonList= new JSONArray(jsonCardStackList);
        int n= jsonList.length();
        CardList[] cardList= new CardList[n];

        for(int i=0;i<n;i++) {

            cardList[i] = JsonToCardStack(jsonList.getJSONObject(i));


        }
        return cardList;
    }



}


