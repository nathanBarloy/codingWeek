
package models;
import database.Database;
import launch.Main;
import learning.LearningAlgo;
import models.*;
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
        Card card1 = new Card("la vérité blesse", "Qui est le plus salé du groupe? ", "Alexis<3");
        Card card2 = new Card("sql", "Qui est le génie du sql? ", "Alexis" );
        Card card3 = new Card("temps", "Quelle heure est-il? ", "L'heure d'une petite pause" );
        CardStack cardstack = new CardStack( "test" , "pour test ");
        cardstack.push(card1);
        cardstack.push(card2);
        cardstack.push(card3);
        this.cardStack = cardStack;
    }

    public void setDatabase() {
        Card card1 = new Card("la vérité blesse", "Qui est le plus salé du groupe? ", "Alexis<3");
        Card card2 = new Card("sql", "Qui est le génie du sql? ", "Alexis");
        Card card3 = new Card("temps", "Quelle heure est-il? ", "L'heure d'une petite pause");
        CardStack cardstack1 = new CardStack("test1", "pour test ");
        cardstack1.push(card1);
        cardstack1.push(card2);
        cardstack1.push(card3);


        CardStack cardstack2 = new CardStack("test2", "pour test ");
        CardStack cardstack3 = new CardStack("test3", "pour test ");
        CardStack cardstack4 = new CardStack("test3", "pour test ");
        CardStack cardstack5 = new CardStack("test2", "pour test ");

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
