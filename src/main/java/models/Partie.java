
package models;
import database.Database;
import launch.Main;
import learning.LearningAlgo;
import models.*;
import views.ControllerVueQuestion;
import models.Player;

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
        return cardStack.pop(); //sera récupéré en sql après

    }

    public Player getPlayer() {
        return player;
    }

    public int getNbCards() {
        return nbCards;
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
