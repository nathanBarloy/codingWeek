
package models;
import database.Database;
import javafx.scene.control.Alert;
import launch.Main;
import learning.LearningAlgo;
import seeds.CardStackSeed;

import java.util.ArrayList;
import java.util.Observable;

public class Partie extends Observable{
    private Player player;
    private CardList cardList;
    private int nbCards;
    private Card CurrentCard;
    private Database database;
    private LearningAlgo learningAlgo;
    private String QuestEnCours;
    private String RepEnCours;
    private String NameEnCours;



    public String getNameEnCours() { return NameEnCours; }

    public String getQuestEnCours() {
        return QuestEnCours;
    }

    public String getRepEnCours() {
        return RepEnCours;
    }

    //----------------------------------------------------------------------------------------------
    //Constructeur
    public Partie(Player player, CardList cardList) {
        this.player = player;
        this.cardList = cardList;
        this.database = new Database();
        this.nbCards = cardList.getNbCards();


    }
    //-----------------------------------------------------------------------------------------------
    //Getter
    public CardList getCardList() {

        return cardList;
    }

    public Card getCurrentCard() {

        return CurrentCard;
    }



    public Card getCurrentCard( String namedeck) {
        return CurrentCard; //sera récupéré en sql après
/*
        //en dur
        List<CardList> cardStackList = database.getCardList("test1");
        CardList cardList = cardStackList.get(0);
        //fin de en dur
        Card card = cardList.getCard();
        return card;
*/
    }

    public Player getPlayer() {
        return player;
    }

    public int getNbCards() {
        return nbCards;
    }

    public Database getDatabase() {
        return database;
    }
//-----------------------------------------------------------------------------------------------
    //Setter


    public void setCurrentCard(Card card) {
        this.CurrentCard = card;
    }

    public void setCardList(CardList cardList) {
        CardStackSeed cardStackSeed = new CardStackSeed(cardList,player);
        cardStackSeed.seed();
    }

    public void setDatabase() {
        CardList cardstack1 = new CardList("test1", "pour test ",player);
        CardStackSeed cardStackSeed = new CardStackSeed(cardstack1,player);
        cardStackSeed.seed();

        CardList cardstack2 = new CardList("test2", "pour test ",player);
        CardList cardstack3 = new CardList("test3", "pour test ",player);
        CardList cardstack4 = new CardList("test3", "pour test ",player);
        CardList cardstack5 = new CardList("test2", "pour test ",player);

        database = new Database();
        database.add(cardstack1);
        database.add(cardstack2);
        database.add(cardstack3);
        database.add(cardstack4);
        database.add(cardstack5);


    }

    public void addCard(String NomDeck,Card card){
        this.database.addCard(NomDeck,card);
    }
    //fin du code en dur
    //------------------------------------------------------------------------------------------------------------------

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setNbCards(int nbCards) {
        this.nbCards = nbCards;
    }
    //------------------------------------------------------------------------------------------------------------------
    //adder
    public void addCardCardList(String nameCardList , Card card) {
        this.database.addCardCardList(nameCardList ,  card);
    }
    //----------------------------------------------------------------------------------------------
    //Autres fonctions

    public void init() {
        setChanged();
        notifyObservers();
    }

    public void NvQuest() {
        this.CurrentCard = cardList.pop();
        if (this.CurrentCard != null) {
            this.CurrentCard.setType("question");
            setChanged();
            notifyObservers();
        } else {
            Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setTitle("ERREUR");
            alertt.setHeaderText("Il n'y a plus de cartes, nous allons quitter");
            String mmessage = "";

            alertt.setContentText(mmessage);
            alertt.showAndWait();
            Main.main.switchScene("/views/VueMenu.fxml");
        }
    }
    public void valider() {

        this.CurrentCard.setType("reponse");
        setChanged();
        notifyObservers();
    }

    public void initImg() {
        setChanged();
        notifyObservers();
    }

    public void buttons() {
        //setChanged();
        //notifyObservers();
    }


    public void setRepEnCours(String repEnCours) {
        RepEnCours = repEnCours;
    }

    public void setQuestEnCours(String questEnCours) {
        QuestEnCours = questEnCours;
    }

    public ArrayList<String> getDeckName() {
        ArrayList<String> res = database.getDeckName();
        return res;
    }

    public void setNameEnCours(String nameEnCours) {
        this.NameEnCours = nameEnCours;
    }
}
