package com.quiz.game.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoadMap {
  private String questionId;
  private Answer answerId;
  private int point;

    public RoadMap(String questionId, Answer answerId, int point) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoadMap)) return false;

        RoadMap roadMap = (RoadMap) o;

        if (!getQuestionId().equals(roadMap.getQuestionId())) return false;
        return getAnswerId().equals(roadMap.getAnswerId());
    }

    @Override
    public int hashCode() {
        int result = getQuestionId().hashCode();
        result = 31 * result + getAnswerId().hashCode();
        return result;
    }
}
