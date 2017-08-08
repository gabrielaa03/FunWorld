package com.gabrielaangebrandt.funworld.models.data_model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Gabriela on 30.5.2017..
 */

public class Player extends RealmObject {
    @PrimaryKey
    private String username;
    private String name;
    private String password;
    private String question;
    private String email;
    private String answer;

    public Player(){}

    public Player(String name, String username, String password, String email, String question, String answer){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email= email;
        this.question = question;
        this.answer = answer;
    }

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

}
