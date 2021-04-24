package com.quiz.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.quiz.game.entity.*;
import com.quiz.game.manager.GameManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;


@SpringBootTest
class GameApplicationTests {


    private GameManager gameManager;
    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void prepare() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("gameManager.txt")).getFile());
        String data = FileUtils.readFileToString(file, "UTF-8");
        gameManager = objectMapper.readValue(data, GameManager.class);
        Assertions.assertNotNull(gameManager.getGames());
    }

    @Test
    public void answerTest_game1() {

        String gameId = "8b5c60a9-3333-46c8-b7fb-c006fc0f3040";
        String questionId = "8caeba30-5ecb-4293-ab9f-a56e460c10b1";
        String answerId = "b3a864be-2563-4450-baa2-4a7b5ce019d4";
        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.setGameId(gameId);
        answerRequest.setUserName("Anna");
        answerRequest.setQuestionId(questionId);
        answerRequest.setAnswerId(answerId);

        AnswerResponse expectedAnswerResponse = new AnswerResponse(AnswerStatus.Correct, 44);
        AnswerResponse answerResponse = gameManager.answerQuestion(answerRequest);
        validateAnswerResponse(answerResponse, expectedAnswerResponse);
        Map<String, Integer> userScore = Maps.newHashMap();
        userScore.put("Anna", 44);
        Game game = gameManager.getGames().get(gameId);
        validateLeaderBoard(game.getLeaderboard().getUserByPoints(), userScore);


    }


    @Test
    public void answerTest_game1_two_qiestions() {

        String gameId = "8b5c60a9-3333-46c8-b7fb-c006fc0f3040";
        String questionId = "8caeba30-5ecb-4293-ab9f-a56e460c10b1";
        String answerId = "b3a864be-2563-4450-baa2-4a7b5ce019d4";
        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.setGameId(gameId);
        answerRequest.setUserName("Anna");
        answerRequest.setQuestionId(questionId);
        answerRequest.setAnswerId(answerId);

        AnswerResponse expectedAnswerResponse = new AnswerResponse(AnswerStatus.Correct, 44);
        AnswerResponse answerResponse = gameManager.answerQuestion(answerRequest);
        validateAnswerResponse(answerResponse, expectedAnswerResponse);
        Map<String, Integer> userScore = Maps.newHashMap();
        userScore.put("Anna", 44);
        Game game = gameManager.getGames().get(gameId);
        validateLeaderBoard(game.getLeaderboard().getUserByPoints(), userScore);



        String questionId2 = "1b8884bb-755e-439d-a564-047b0bc21d75";
        String answerId2 = "c8b650df-bb5e-4b56-a9a8-4c2ab0a81e8d";
        answerRequest.setQuestionId(questionId2);
        answerRequest.setAnswerId(answerId2);

        expectedAnswerResponse = new AnswerResponse(AnswerStatus.Correct, 100);
        answerResponse = gameManager.answerQuestion(answerRequest);
        validateAnswerResponse(answerResponse, expectedAnswerResponse);
        userScore.put("Anna", 144);
        validateLeaderBoard(game.getLeaderboard().getUserByPoints(), userScore);
    }

    @Test
    public void answerTest_game2() {
        String gameId = "3d66548a-4f29-4621-854f-f361babe2250";
        String questionId = "6cfe225c-1604-4741-86b6-da5f5d217aa8";
        String answerId = "641b35f1-ced2-438d-8ba0-b4083922fbe0";
        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.setGameId(gameId);
        answerRequest.setUserName("Guy");
        answerRequest.setQuestionId(questionId);
        answerRequest.setAnswerId(answerId);

        AnswerResponse expectedAnswerResponse = new AnswerResponse(AnswerStatus.Correct, 100);
        AnswerResponse answerResponse = gameManager.answerQuestion(answerRequest);
        validateAnswerResponse(answerResponse, expectedAnswerResponse);
        Map<String, Integer> userScore = Maps.newHashMap();
        userScore.put("Guy", 100);
        Game game = gameManager.getGames().get(gameId);
        validateLeaderBoard(game.getLeaderboard().getUserByPoints(), userScore);


    }

    @Test
    public void answerTest_2games() {
        answerTest_game1();
        answerTest_game2();
    }





    private void validateLeaderBoard(Map<String, Integer> actualRes, Map<String, Integer> expectRes) {
        expectRes.keySet().forEach(k -> Assertions.assertEquals(expectRes.get(k).intValue(), actualRes.get(k).intValue()));
    }

    private void validateAnswerResponse(AnswerResponse actualRes, AnswerResponse expectRes) {
        Assertions.assertSame(actualRes.getAnswerStatus(), expectRes.getAnswerStatus());
        Assertions.assertEquals(expectRes.getPoints(), actualRes.getPoints());

    }


}





