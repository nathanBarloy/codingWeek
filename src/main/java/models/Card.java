package models;

public class Card {
    private  String name;
    private  String text;
    private  String answer;
    private String type;
    private String author;
    private int succes;
    private int fail ;
    private int  user_succes;

    public int getNbBonnesReponses() {
        return NbBonnesReponses;
    }

    public int getNbMoyennesReponses() {
        return NbMoyennesReponses;
    }

    public int getNbFaussesReponses() {
        return NbFaussesReponses;
    }

    private int user_fail;
    private int state;
    private int id;

    public void setNbBonnesReponses() {
        NbBonnesReponses++;
    }

    public void setNbMoyennesReponses() {
        NbMoyennesReponses++;
    }

    public void setNbFaussesReponses() {
        NbFaussesReponses++;
    }


    public void setNbBonnesReponses(int i) {
        NbBonnesReponses = i;
    }

    public void setNbMoyennesReponses(int i) {
        NbMoyennesReponses = i;
    }

    public void setNbFaussesReponses(int i) {
        NbFaussesReponses=  i;
    }



    private int NbBonnesReponses;
    private int NbMoyennesReponses;
    private int NbFaussesReponses;
    //------------------------------------------------------------------------------------------------------------------
    //Constructor


    public Card (String name, String question, String answer, String player){
        this.name=name;
        this.text=question;
        this.answer=answer;
        this.author=player;
        this.type = "question";
        this.state = 0;

    }
    public Card (String name, String question, String answer, Player player){
        this(name,question,answer,player.getUsername());

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //------------------------------------------------------------------------------------------------------------------
    //getter
    public String getAuthor() {
        return author;
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
    public int getUser_succes() {
        return user_succes;
    }
    public int getUser_fail() {
        return user_fail;
    }
    public int getFail() {
        return fail;
    }


    public int getSucces() {
        return succes;
    }
    public String getText() {
        return text;
    }

    public int getState() {
        return state;
    }

    //------------------------------------------------------------------------------------------------------------------
    //setter
    public void setType(String type) {
        this.type = type;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSucces(int succes) {
        this.succes = succes;
    }
    public void setFail(int fail) {
        this.fail = fail;
    }
    public void setUser_succes(int user_succes) {
        this.user_succes = user_succes;
    }

    public void setUser_fail(int user_fail) {
        this.user_fail = user_fail;
    }

    public void setState(int state) {
        this.state = this.state + state;
    }

    public void setState(int state, int state2) {
        this.state = state2;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                '}';
    }
}
