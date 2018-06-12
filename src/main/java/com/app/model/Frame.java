package com.app.model;

public class Frame {

    private int firstRoll;
    private int secondRoll;
    private int totalFrameScore;
    private int bonusScore;
    private boolean isStrike;
    private boolean isSpare;
    private boolean showScore;
    private GameScore tenPinGameScore;

    public void setTotalFrameScore(int totalFrameScore) {
        this.totalFrameScore = totalFrameScore;
    }

    public Frame(GameScore tenPinGameScore){
        setFirstRoll(-1);
        setSecondRoll(-1);
        this.tenPinGameScore = tenPinGameScore;

    }

    public int getFirstRoll() {
        return firstRoll;
    }
    public void setFirstRoll(int firstRoll) {
        this.firstRoll = firstRoll;
    }

    public void setSecondRoll(int secondRoll) {
        this.secondRoll = secondRoll;
    }

    public int getSecondRoll() {
        return secondRoll;
    }

    public int getTotalFrameScore() {
        return totalFrameScore;
    }

    public boolean isBowled() {
        if(isStrike() || getSecondRoll() != -1){
            return true;
        }
        return false;
    }

    private boolean isStrike() {

        return getFirstRoll() == 10;
    }

    public void setScore(int pinsDown) {
        if (getFirstRoll() == -1) {
            setFirstRoll(pinsDown);
            if(pinsDown == 10){
                setStrike(true);
                setTotalFrameScore(getTotalFrameScore() + 10);
                setBonusScore(2);
            }
            return;

        }
        if (getSecondRoll() == -1 && !isStrike){
            setSecondRoll(pinsDown);
            if(getFirstRoll() + getSecondRoll() == 10){
                setSpare(true);
                setTotalFrameScore(getTotalFrameScore() + 10);
                setBonusScore(1);
            }
            return;
        }
    }

    public void updateTotalScore(int pinsDown) {
        //TODO
        setTotalFrameScore(getTotalFrameScore() + pinsDown);
        showTotalScore(pinsDown);
        setBonusScore(getBonusScore()-1);

        if(isCalculated()){
            int totalScore = tenPinGameScore.getTotalScore();
            int totalFrameScore = this.getTotalFrameScore();

            setTotalFrameScore(totalFrameScore + totalScore);
            tenPinGameScore.setTotalScore(totalScore + totalFrameScore);
        }


        System.out.println("Total score: "+this.getTotalFrameScore());
    }

    private void showTotalScore(int pinsDown) {
        if(isCalculated(pinsDown)){
            setShowScore(true);
        }
    }

    public boolean isCalculated(int frameNum) {
        return getBonusScore() == 0 && getFirstRoll() != -1;
    }
    public boolean isCalculated() {
        return getBonusScore() == 0 && getFirstRoll() != -1;
    }


    public int getBonusScore() {
        return bonusScore;
    }

    public void setBonusScore(int bonusScore) {
        this.bonusScore = bonusScore;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    public boolean isShowScore() {
        return showScore;
    }

    public void setShowScore(boolean showScore) {
        this.showScore = showScore;
    }

    public GameScore getTenPinGameScore() {
        return tenPinGameScore;
    }

    public void setTenPinGameScore(GameScore tenPinGameScore) {
        this.tenPinGameScore = tenPinGameScore;
    }
}

