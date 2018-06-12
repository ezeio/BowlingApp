package com.app.model;

public interface GameScore {
    void calculate(int frameNum, int pindsDown);
    int getTotalScore();
    void setTotalScore(int score);
    boolean isLastFrame();
}
