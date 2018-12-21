package queries;

import database.Database;

public class QueryCheckLogin extends Query {


    public QueryCheckLogin(Database db, String player, String password) {
        super("checkLogin",db);
        this.parameters+="username="+player+"&password="+password;
    }



}
