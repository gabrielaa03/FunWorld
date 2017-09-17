package com.gabrielaangebrandt.funworld.models.data_model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Player extends RealmObject {
    @PrimaryKey
    private String username;
    private String name;
    private String password;
    private String question;
    private String email;
    private String answer;
    private String hsMemory;
    private int hsPicado;
    private int hsTilt;

    public Player(String name, String username, String password, String email, String question, String answer, String hsMemory, int hsPicado, int hsTilt) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.question = question;
        this.email = email;
        this.answer = answer;
        this.hsMemory = hsMemory;
        this.hsPicado = hsPicado;
        this.hsTilt = hsTilt;
    }

    public Player(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHsMemory() {
        return hsMemory;
    }

    public void setHsMemory(String hsMemory) {
        this.hsMemory = hsMemory;}

    public int getHsPicado() {
        return hsPicado;
    }

    public void setHsPicado(int hsPicado) {
        this.hsPicado = hsPicado;
    }

    public int getHsTilt() {
        return hsTilt;
    }

    public void setHsTilt(int hsTilt) {
        this.hsTilt = hsTilt;
    }
}
