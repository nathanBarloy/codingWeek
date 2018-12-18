
package models;
import database.Database;
import launch.Main;
import learning.LearningAlgo;
import models.*;
import seeds.CardStackSeed;
import views.ControllerVueQuestion;
import models.Player;

import java.util.List;
import java.util.Observable;

public class Partie extends Observable{
    private Player player;
    private CardStack cardStack;
    private int nbCards;
    private Card CurrentCard;
    private Database database;
    private LearningAlgo learningAlgo;


    //----------------------------------------------------------------------------------------------
    //Constructeur
    public Partie(Player player, CardStack cardStack) {
        this.player = player;
        this.cardStack = cardStack;
        this.database = new Database();
        this.nbCards = cardStack.getNbCards();


    }
    //-----------------------------------------------------------------------------------------------
    //Getter
    public CardStack getCardStack() {

        return cardStack;
    }

    public Card getCurrentCard() {

        return CurrentCard;
    }



    public Card getCurrentCard( String namedeck) {
        return CurrentCard; //sera récupéré en sql après
/*
        //en dur
        List<CardStack> cardStackList = database.getCardStack("test1");
        CardStack cardStack = cardStackList.get(0);
        //fin de en dur
        Card card = cardStack.getCard();
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

    public void setCardStack(CardStack cardStack) {
        CardStackSeed cardStackSeed = new CardStackSeed(cardStack,player);
        cardStackSeed.seed();
    }

    public void setDatabase() {
        CardStack cardstack1 = new CardStack("test1", "pour test ",player);
        CardStackSeed cardStackSeed = new CardStackSeed(cardstack1,player);
        cardStackSeed.seed();

        CardStack cardstack2 = new CardStack("test2", "pour test ",player);
        CardStack cardstack3 = new CardStack("test3", "pour test ",player);
        CardStack cardstack4 = new CardStack("test3", "pour test ",player);
        CardStack cardstack5 = new CardStack("test2", "pour test ",player);

        database = new Database();
        database.add(cardstack1);
        database.add(cardstack2);
        database.add(cardstack3);
        database.add(cardstack4);
        database.add(cardstack5);


    }

    //fin du code en dur
    //------------------------------------------------------------------------------------------------------------------

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setNbCards(int nbCards) {
        this.nbCards = nbCards;
    }
    //----------------------------------------------------------------------------------------------
    //Autres fonctions

    public void init() {
        setChanged();
        notifyObservers();
    }

    public void NvQuest() {
        this.CurrentCard = cardStack.pop();
        if (this.CurrentCard != null) {
            this.CurrentCard.setType("question");
            setChanged();
            notifyObservers();
        } else {
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


}
