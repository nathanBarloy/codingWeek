package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Card;
import models.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;


public class JSONUserParser {




    public static String userToJson(Player user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("JSONS/file"+user.getUsername()+".json"), user);
        return mapper.writeValueAsString(user);
    }

    public static Player JsonToUser(JSONObject jsonUser){


        String name=jsonUser.getString("username");
        String description=jsonUser.getString("description");
        return new Player(name,description);
    }

    public static Player JsonToUser(String jsonUser){
        return JsonToUser(new JSONObject(jsonUser));
    }

    public static Player[] JsonToUserList(String jsonUserList){

        JSONArray jsonList= new JSONArray(jsonUserList);
        int n= jsonList.length();
        Player[] userList= new Player[n];
        for(int i=0;i<n;i++) {

            userList[i] = JsonToUser(jsonList.getJSONObject(i));
        }
        return userList;
    }



}
