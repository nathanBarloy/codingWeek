package queries;

public class QueryCheckLogin extends Query {


    public QueryCheckLogin(String player,String password) {
        super("checkLogin");
        this.parameters+="username="+player+"&password="+password;
    }



}
