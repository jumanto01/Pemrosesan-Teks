package Class;

import java.util.*;

public class ArrayListRefDok {

    //private ArrayList <Dokumen> ALRefDok;
    private ArrayList<RefDok> ALRefDok;

    public ArrayListRefDok() {
        ALRefDok = new ArrayList<RefDok>();
    }

    public void setData(RefDok o) {
        ALRefDok.add(o);
    }

    public ArrayList<RefDok> getListRefDok() {
        return ALRefDok;
    }
}
