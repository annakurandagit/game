package com.quiz.game.entity;

import lombok.*;

import java.util.UUID;


@ToString
@Getter
@Setter
public class Answer {


    private  String answerId;
    private  String answer;


    public Answer(String answer) {
        this.answerId = UUID.randomUUID().toString();
        this.answer = answer;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;

        Answer answer = (Answer) o;

        return answerId.equals(answer.answerId);
    }

    @Override
    public int hashCode() {
        return answerId.hashCode();
    }
}


