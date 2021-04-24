package com.quiz.game.entity;

import lombok.*;



@Getter
@Setter
public class AnswerRequest {

    private String userName;

    private String gameId;

    private String questionId;

    private String answerId;

}
