package models;

public class Player {
    private String username;
    private String description;

    public Player(String username, String description) {
        this.username = username;
        this.description = description;
    }

    public Player(String username) {
        this(username,"");
    }

    public Player(){}
    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return ("nom du joueur : " + username + "\ndescription : ");
    }
}
