package com.quiz.game.manager;
import com.google.common.collect.Maps;
import com.quiz.game.db.GameManagerInputInitializer;
import com.quiz.game.entity.*;
import lombok.*;
import org.springframework.stereotype.Service;
import java.util.*;


@Getter
@Setter
@ToString
@Service
@NoArgsConstructor
public class GameManager {
    private Map<String, Game> games = Maps.newHashMap();

    public void addGame(Game game) {
        games.putIfAbsent(game.getGameId(), game);
    }

    public void init() {
        GameManagerInputInitializer gameManagerInputInitializer = new GameManagerInputInitializer();
        Set<Game> data = gameManagerInputInitializer.initialize();
        data.forEach(this::addGame);

    }

    public Map<String, Integer> getGameLeaderboard(String gameId) {
        validateGame(gameId);
        Game game = games.get(gameId);
        return game.getLeaderboard().getUserByPoints();

    }


    public Map<String, List<RoadMap>> getGameRoadMap(String gameId) {
        validateGame(gameId);
        Game game = games.get(gameId);
        return game.getPlayersToRoadMap();

    }


    public AnswerResponse answerQuestion(AnswerRequest answerRequest) {
        validateAnswerRequest(answerRequest);
        RoadMap roadMap = games.get(answerRequest.getGameId()).addAnswer(answerRequest);
        AnswerStatus answerStatus = roadMap.getPoint() == 0 ? AnswerStatus.Wrong : AnswerStatus.Correct;
        return new AnswerResponse(answerStatus, roadMap.getPoint());
    }


    private void validateAnswerRequest(AnswerRequest answerRequest) {
        validateGame(answerRequest.getGameId());
        validateQuestion(answerRequest);
    }

    private void validateQuestion(AnswerRequest answerRequest) {
        if (!games.get(answerRequest.getGameId()).getQuestionMap().containsKey(answerRequest.getQuestionId()))
            throw new RuntimeException("Question id invalid");
    }

    private void validateGame(String gameID) {
        if (!games.containsKey(gameID))
            throw new RuntimeException("Game id invalid " + gameID);
    }


}
