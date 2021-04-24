package com.quiz.game.entity;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
public class Leaderboard {
   Map<String,Integer> userByPoints = Maps.newHashMap();



    public void updateLeaderBoard(String user,int points){
        if(!userByPoints.containsKey(user)){
            userByPoints.put(user,0);
        }
        userByPoints.put(user, userByPoints.get(user) + points);
    }




}
