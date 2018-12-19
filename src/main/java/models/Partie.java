
package models;
import database.Database;
import javafx.scene.control.Alert;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import launch.Main;
import learning.LearningAlgo;
import seeds.CardStackSeed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    public boolean timeout = false;
    private String deckEnCours;


    public String getNameEnCours() { return NameEnCours; }

    public String getQuestEnCours() {
        return QuestEnCours;
    }

    public String getRepEnCours() {
        return RepEnCours;
    }

    //----------------------------------------------------------------------------------------------
    //Constructeur
    public Partie(Player player, CardList cardList) throws IOException {
        this.player = player;
        this.cardList = cardList;
        this.database = new Database();
        this.nbCards = cardList.getNbCards();
        this.database.setDatabase();
        //System.out.println(this.database.getListCardList().size() + "decks par défault");


    }

    public Partie(Player player) throws IOException {
        this.player = player;
        this.cardList = new CardList("Default","Deck avec des cartes par défaut");
        CardStackSeed cs = new CardStackSeed(cardList);
        cs.seed();
        this.database = new Database();
        this.nbCards = cardList.getNbCards();
        this.database.setDatabase();
        //System.out.println(this.database.getListCardList().size() + "decks par défault");
    }

    public Partie() throws IOException {
        this(new Player("perso test"));
    }

    //-----------------------------------------------------------------------------------------------
    //Getter

    public ArrayList<String> getListeDeck(){
        ArrayList<String> res = new ArrayList<String>();
        List<CardList> temp = this.database.getListCardList();
        for (CardList c : temp){
            System.out.println("getListeDeck:" + c.getName());
            res.add(c.getName());
        }
        return res;
    }
    public CardList getCardList() {

        return cardList;
    }

    public Card getCurrentCard() {

        return CurrentCard;
    }



    public Card getCurrentCard( String namedeck) {
        return CurrentCard;
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



    public void addCard(String NomDeck,Card card){
        this.database.addCard(NomDeck,card);
    }
    public void SupprimerCard(String NomDeck,Card card){
        this.database.SupressCard(NomDeck,card);
        setChanged();
        notifyObservers();

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

    public void NvQuest(String deck) {
        this.deckEnCours = deck;
        this.CurrentCard = this.database.pop(deckEnCours);

        if (this.CurrentCard != null) {
            this.CurrentCard.setType("question");
            setChanged();
            notifyObservers();
        } else {
            Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setTitle("ERREUR");
            alertt.setHeaderText("Vous avez fini le deck!");
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

    public Card getCard(String temp, String currentDeck) {

        return this.database.getCard(temp,currentDeck);

    }

    public void Choisir() {
        setChanged();
        notifyObservers();
    }

    public ArrayList<String> getListeCarte(String currentDeck) {
        return this.database.getListeCarte(currentDeck);
    }

    public String getDeckEnCours() {
        return this.deckEnCours;
    }

    public void setDeckEnCours(String deckEnCours) {
        this.deckEnCours = deckEnCours;
    }

    public void reset() {
        this.database.reset();
    }

    public void deleteCardList(CardList cardList) {
        this.database.deleteCardList(cardList);
    }
    public void deleteCardList(String name) {
        this.database.deleteCardList(name);
    }

    public void addDeck(String text) {
        this.database.addDeck(text,"une description",this.player.getUsername());
        setChanged();
        notifyObservers();
    }

    public String getFirstDeck() {
        return this.database.getFirstDeck();

    }
}
