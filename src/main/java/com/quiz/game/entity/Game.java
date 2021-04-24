package com.quiz.game.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.util.*;

import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor
public class Game {
    private String gameId;
    private Map<String, List<RoadMap>> playersToRoadMap;
    private Leaderboard leaderboard;
    private Map<String, Question> questionMap;

    public Game(Set<Question> questions) {
        this.gameId = UUID.randomUUID().toString();
        playersToRoadMap = Maps.newHashMap();
        questionMap = initQuestions(questions);
        this.leaderboard = new Leaderboard();
    }

    private Map<String, Question> initQuestions(Set<Question> questions) {
        if (questions != null && !questions.isEmpty()) {
            return questions.stream().collect(Collectors.toMap(Question::getQuestionId, Function.identity()));
        } else {
            throw new RuntimeException("Questions list null or empty");
        }
    }

    private RoadMap addAnswer(String playerId, RoadMap roadMap) {
        playersToRoadMap.putIfAbsent(playerId, Lists.newArrayList());
        playersToRoadMap.get(playerId).add(roadMap);
        return roadMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;

        Game game = (Game) o;

        return gameId.equals(game.gameId);
    }

    @Override
    public int hashCode() {
        return gameId.hashCode();
    }

    public RoadMap addAnswer(AnswerRequest answerRequest) {
        RoadMap roadMap = createRoadMap(answerRequest);
        leaderboard.updateLeaderBoard(answerRequest.getUserName(),roadMap.getPoint());
        return addAnswer(answerRequest.getUserName(), roadMap);
    }

    private RoadMap createRoadMap(AnswerRequest answerRequest) {
        Answer answer = getAnswer(answerRequest.getQuestionId(), answerRequest.getAnswerId());
        int points = questionMap.get(answerRequest.getQuestionId()).getAnswersToPoints().get(answerRequest.getAnswerId()).get(answer);
        return new RoadMap(answerRequest.getQuestionId(), answer, points);

    }

    private Answer getAnswer(String questionId, String answerId) {
       return questionMap.get(questionId).getAnswersToPoints().get(answerId).keySet().iterator().next();

    }
}
