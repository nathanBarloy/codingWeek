package models;

public class Card {
    private  String name;
    private  String text;
    private  String answer;

    public Card (String name, String text, String answer){
        this.name=name;
        this.text=text;
        this.answer=answer;
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
