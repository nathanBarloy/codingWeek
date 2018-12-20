
package models;
import database.Database;
import javafx.scene.control.Alert;
import launch.Main;
import learning.LearningAlgo;
import seeds.CardStackSeed;
import statistic.Stat;

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
    private String currentDeck;
    private Stat stat;
    private boolean local;


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
        this.local = false;
        //System.out.println(this.database.getListCardList().size() + "decks par défault");


    }

    public Partie(Player player) throws IOException {
        this.player = player;
        this.cardList = new CardList("Default","Deck avec des cartes par défaut");

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
            //System.out.println("getListeDeck:" + c.getName());
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

    public double getProgressCurrentDeck(){
        this.stat = new Stat();
        /*
        System.out.println("current card dans partie  " +this.currentDeck);
        System.out.println("partie trouvé  " +this.database.getCardListString( this.currentDeck).getName());
        System.out.println("resultat 1ère carte " +this.database.getCardListString( this.currentDeck).getCard().getState());
        */
        return stat.getProgession(this.database.getCardListString( this.currentDeck));
    }
    public double getProgressDeck(String namedeck){
        this.stat = new Stat();
        /*
        System.out.println("current card dans partie  " +this.currentDeck);
        System.out.println("partie trouvé  " +this.database.getCardListString( this.currentDeck).getName());
        System.out.println("resultat 1ère carte " +this.database.getCardListString( this.currentDeck).getCard().getState());
        */
        return stat.getProgession(this.database.getCardListString( namedeck));
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
        this.database.supressCard(NomDeck,card);
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
        this.currentDeck = deck;
        this.CurrentCard = this.database.pop(currentDeck);

        if (this.CurrentCard != null) {
            this.CurrentCard.setType("question");
            setChanged();
            notifyObservers();
        } else {
            Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setTitle("ERREUR");
            alertt.setHeaderText("Vous avez fini le deck!");
            this.reset();
            String mmessage = "";

            alertt.setContentText(mmessage);
            alertt.showAndWait();

            Main.main.switchScene("/views/VueMenu.fxml");
        }
    }

    public int getScore(String currentDeck,Card c){
        return this.database.getScore(currentDeck,c);
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

    public String getcurrentDeck() {
        return this.currentDeck;
    }

    public void setcurrentDeck(String currentDeck) {
        this.currentDeck = currentDeck;
    }

    public void resetScores(String nomdeck) {
        database.resetScores(nomdeck);
    }

    public void reset() {
        //System.out.println("resetin");
        this.database.reset();
    }

    public void deleteCardList(CardList cardList) {
        this.database.deleteCardList(cardList);
    }
    public void deleteCardList(String name) {
        this.database.deleteCardList(name);
        init();
    }

    public void addDeck(String text) {
        this.database.addDeck(text,"une description",this.player.getUsername());
        setChanged();
        notifyObservers();
    }

    public String getFirstDeck() {
        return this.database.getFirstDeck();

    }

    public void setScore(String currentDeck, Card carte,int Score) {
        this.database.setScore(currentDeck,carte,Score);
    }


    public void importDatabaseLocal(){
        this.database.importDatabaselocal();

    }
    public void exportDatabaseLocal(){
        this.database.exportDatabaselocal();

    }


    public boolean verifierReponse(String reponse,String averifier) {
        String s1 = reponse.toLowerCase();
        String s2 = averifier.toLowerCase();
        int dLev = distanceLevenshtein(s1,s2);
        //dLev = dLev/( (double) Math.max(s1.length(),s2.length()) );
        double n = (float) Math.min(s1.length(),s2.length());
        double nbErr = n/10 + 3.0*Math.sqrt(n)/10.0;
        return (dLev <= nbErr);
    }


    public int distanceLevenshtein(String s1,String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        //initialisation de la matrice d
        List<List<Integer>> d = new ArrayList<List<Integer>>(n1+1);
        for (int i=0;i<n1+1;i++) {
            //System.out.println("indice : " + i);
            d.add(new ArrayList<Integer>(n2+1));
        }

        int coutSub;
        for (int i=0;i<n1+1;i++) {
            d.get(i).add(i);
        }
        for (int j=0; j<n2+1;j++) {
            d.get(0).add(j);
        }

        for (int i=1; i<n1+1;i++) {
            for (int j=1; j<n2+1;j++) {
                if (s1.charAt(i-1)==s2.charAt(j-1)) {
                    coutSub = 0;
                } else {
                    coutSub = 1;
                }

                int tempVal = Math.min( Math.min(d.get(i-1).get(j)+1 , d.get(i).get(j-1)+1) , d.get(i-1).get(j-1)+coutSub );
                d.get(i).add(tempVal);
            }
        }

        return d.get(n1).get(n2);
    }
    /*entier DistanceDeLevenshtein(caractere chaine1[1..longueurChaine1],
                                 caractere chaine2[1..longueurChaine2])
    // d est un tableau de longueurChaine1+1 rangées et longueurChaine2+1 colonnes
    // d est indexé à partir de 0, les chaînes à partir de 1
    déclarer entier d[0..longueurChaine1, 0..longueurChaine2]
    // i et j itèrent sur chaine1 et chaine2
    déclarer entier i, j, coûtSubstitution

    pour i de 0 à longueurChaine1
    d[i, 0] := i
    pour j de 0 à longueurChaine2
    d[0, j] := j

    pour i de 1 à longueurChaine1
    pour j de 1 à longueurChaine2
    si chaine1[i-1] = chaine2[j-1] alors coûtSubstitution := 0
    sinon coûtSubstitution := 1
    d[i, j] := minimum(
            d[i-1, j  ] + 1,                 // effacement du nouveau caractère de chaine1
            d[i,   j-1] + 1,                 // insertion dans chaine2 du nouveau caractère de chaine1
            d[i-1, j-1] + coûtSubstitution   // substitution
    )

    renvoyer d[longueurChaine1, longueurChaine2]
    */
}

