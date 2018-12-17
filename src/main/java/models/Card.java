package models;

public class Card {
    private  String name;
    private  String text;
    private  String answer;
    public String type = "question";

    public Card (String name, String question, String answer){
        this.name=name;
        this.text=question;
        this.answer=answer;
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;

    }
}
