package models;

public class Card {
    private  String name;
    private  String text;
    private  String answer;

    Card (String name, String text, String answer){
        this.name=name;
        this.texte=texte;
    }

    public String getName() {
        return name;
    }
    

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }
}
