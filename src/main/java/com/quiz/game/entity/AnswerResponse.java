package com.quiz.game.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerResponse {
    private AnswerStatus answerStatus;
    private int points;
}
