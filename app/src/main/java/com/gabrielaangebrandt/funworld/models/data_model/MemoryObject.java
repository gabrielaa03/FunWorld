package com.gabrielaangebrandt.funworld.models.data_model;

/**
 * Created by Plava tvornica on 10.8.2017..
 */

public class MemoryObject {
    private int ID;
    private boolean isClicked;
    private boolean isMatched;
    private String alphaCode;

    public MemoryObject(int ID, boolean isClicked, boolean isMatched, String alphaCode) {
        this.ID = ID;
        this.isClicked = isClicked;
        this.isMatched = isMatched;
        this.alphaCode = alphaCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public String getAlphaCode() { return alphaCode; }

    public void setAlphaCode(String alphaCode) { this.alphaCode = alphaCode; }
}
