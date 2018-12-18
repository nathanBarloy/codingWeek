package views;

import models.Partie;

import java.util.Observable;
import java.util.Observer;

public class VueDecks implements Observer {

    private Partie partie;

    public VueDecks(Partie p){
        super();
        this.partie = p;
        this.partie.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
