package database;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<String> listdecks;


    public Database() {
    listdecks=new ArrayList<String>();
    }

    public List<String> getListdecks() {
        return listdecks;
    }

    public void setListdecks(List<String> listdecks) {
        this.listdecks = listdecks;
    }
}
