package com.example.dotdot;

public class Member {
    private String name;
    private String phone;
    private String password;
    private String hintQuestion;
    private String hintAnswer;
    Member member;

    public Member() {

    }

    public Member(String name, String password, String phone, String hintQuestion, String hintAnswer) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.hintQuestion = hintQuestion;
        this.hintAnswer = hintAnswer;
    }

    public String getHintQuestion() { return hintQuestion; }

    public String getHintAnswer() { return hintAnswer; }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() { return password; }

}
