package com.quiz.game.entity;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.*;
import org.apache.commons.lang3.StringUtils;


import java.util.Map;
import java.util.UUID;


@Getter
@ToString
@NoArgsConstructor
public class Question {
    private String questionId;
    private String question;
    private Map<String, Map<Answer, Integer>> answersToPoints;

    public Question(String question, Map<Answer, Integer> answersToPoints) {
        validate(answersToPoints, question);
        this.questionId = UUID.randomUUID().toString();
        this.question = question;
        initAnswerToPoints(answersToPoints);
    }

    private void initAnswerToPoints(Map<Answer, Integer> answersToPointsInput) {
        this.answersToPoints = Maps.newHashMap();
        answersToPointsInput.keySet().forEach(answer -> {
            ImmutableMap<Answer, Integer> map = ImmutableMap.of(answer, answersToPointsInput.get(answer));
            String answerId = answer.getAnswerId();
            this.answersToPoints.put(answerId, map);
        });
    }

    private void validate(Map<Answer, Integer> answersToPoints, String question) {
        validateAnswers(answersToPoints);
        validateQuestion(question);

    }

    private void validateQuestion(String question) {
        if (StringUtils.isEmpty(question)) throw new RuntimeException("Question is mandatory ");
    }

    private void validateAnswers(Map<Answer, Integer> answersToPoints) {
        if (answersToPoints == null || answersToPoints.isEmpty())
            throw new RuntimeException("Answers list null or empty ");

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        return questionId.equals(question.questionId);
    }

    @Override
    public int hashCode() {
        return questionId.hashCode();
    }
}
