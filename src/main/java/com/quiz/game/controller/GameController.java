package com.quiz.game.controller;

import com.quiz.game.entity.*;
import com.quiz.game.manager.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/game")

public class GameController {
    @Autowired
    private GameManager gameManager;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Game> initGameManager() {
        gameManager.init();
        return gameManager.getGames();
    }

    @RequestMapping(value = "/leaderboard/{gameId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Integer> getLeaderboard(@PathVariable("gameId") String gameId) {
        return gameManager.getGameLeaderboard(gameId);
    }
    @RequestMapping(value = "/roadMap/{gameId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<RoadMap>> getRoadMap(@PathVariable("gameId") String gameId) {
        return gameManager.getGameRoadMap(gameId);
    }


    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    @ResponseBody
    public AnswerResponse answer(@RequestBody() AnswerRequest answerRequest) {
        return gameManager.answerQuestion(answerRequest);
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
