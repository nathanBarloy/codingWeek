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
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("JSONS/listCardList.json"), listCardList);
        return mapper.writeValueAsString(listCardList);
    }

    public static ArrayList<CardList> JsonToListCardList(String jsonListCardList) throws IOException{
        JSONCardStackParser cardStackParser = new JSONCardStackParser();
        ArrayList<CardList> listArrayList = new ArrayList<CardList>();
        JSONArray jsonArrayList= new JSONArray(jsonListCardList);
        int n= jsonArrayList.length();


        for(int i=0;i<n;i++) {
            listArrayList.add(cardStackParser.JsonToCardStack(jsonArrayList.getJSONObject(i)));
        }
        return listArrayList;
    }





}
