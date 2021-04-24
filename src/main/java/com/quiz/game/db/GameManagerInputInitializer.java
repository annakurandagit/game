package com.quiz.game.db;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.quiz.game.entity.Answer;
import com.quiz.game.entity.Game;
import com.quiz.game.entity.Question;

import java.util.Map;
import java.util.Set;


public class GameManagerInputInitializer {

    public Set<Game> initialize() {
        Set<Game> games = Sets.newHashSet();
        games.add(createGame(createQuestions_game1()));
        games.add(createGame(createQuestions_game2()));
        return games;

    }

    private Game createGame(Set<Question> questions) {
        return new Game(questions);
    }


    private Set<Question> createQuestions_game1() {

        Set<Question> questionSet = Sets.newHashSet();
        Map<Answer, Integer> answers = Maps.newHashMap();
        answers.put(new Answer("Linux os"), 100);
        answers.put(new Answer("Windows os"), 0);
        answers.put(new Answer("don't know"), 0);
        Question question = new Question("Redhat is?", answers);
        questionSet.add(question);

        Map<Answer, Integer> answers1 = Maps.newHashMap();
        answers1.put(new Answer("yes"), 100);
        answers1.put(new Answer("no"), 0);
        answers1.put(new Answer("don't know"), 0);
        Question question1 = new Question("Samsung has android os", answers1);
        questionSet.add(question1);

        return questionSet;


    }


    private Set<Question> createQuestions_game2() {
        Set<Question> questionSet = Sets.newHashSet();
        Map<Answer, Integer> answers = Maps.newHashMap();
        answers.put(new Answer("yes"), 44);
        answers.put(new Answer("no"), 0);
        answers.put(new Answer("don't know"), 0);
        Question question = new Question("The strawberry red", answers);
        questionSet.add(question);

        Map<Answer, Integer> answers1 = Maps.newHashMap();
        answers1.put(new Answer("yes"), 100);
        answers1.put(new Answer("no"), 0);
        answers1.put(new Answer("don't know"), 0);
        Question question1 = new Question("Samsung has android os", answers1);
        questionSet.add(question1);
        return questionSet;

    }
}
