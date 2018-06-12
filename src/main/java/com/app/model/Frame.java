package com.app.model;

public class Frame {

    private int firstRoll;
    private int secondRoll;
    private int thirdRoll;
    private int totalFrameScore;
    private int bonusScore;
    private boolean isStrike;
    private boolean isSpare;
    private boolean showScore;
    private GameScore tenPinGameScore;
    private final int TEN = 10;
    private final int MINUS_ONE = -1;

    public void setTotalFrameScore(int totalFrameScore) {
        this.totalFrameScore = totalFrameScore;
    }

    public Frame(GameScore tenPinGameScore){
        setFirstRoll(MINUS_ONE);
        setSecondRoll(MINUS_ONE);
        setBonusScore(MINUS_ONE);
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
        boolean isBowledFrame = isStrike() || getSecondRoll() != MINUS_ONE;
        if(isBowledFrame && !tenPinGameScore.isLastFrame()){
            return true;
        }
        else if(getBonusScore() == 0){return true;}
        return false;
    }

    private boolean isStrike() {

        return getFirstRoll() == TEN;
    }

    public void setScore(int pinsDown) {

        if (getFirstRoll() == MINUS_ONE) {
            setFirstRoll(pinsDown);
            if(pinsDown == TEN){
                setStrike(true);
                setTotalFrameScore(getTotalFrameScore() + TEN);
                setBonusScore(2);
            }
            return;

        }
        if (getSecondRoll() == MINUS_ONE && !isStrike){
            setSecondRoll(pinsDown);
            if(getFirstRoll() + getSecondRoll() == TEN){
                setSpare(true);
                setTotalFrameScore(getTotalFrameScore() + TEN);//TODO ?
                setBonusScore(1);
            }
            return;
        }
//        else if(getSecondRoll() == MINUS_ONE && isStrike && tenPinGameScore.isLastFrame()){
//            setSecondRoll(pinsDown);
//        }
//
//        if(tenPinGameScore.isLastFrame() && getSecondRoll() != MINUS_ONE){
//            setThirdRoll(pinsDown);
//            setTotalFrameScore(getTotalFrameScore() + pinsDown);
//        }

        updateTotalScore(pinsDown);
    }

    public void updateTotalScore(int pinsDown) {
        //TODO
        setTotalFrameScore(getTotalFrameScore() + pinsDown);
        showTotalScore(pinsDown);
        setBonusScore(getBonusScore() -1);

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
        return getBonusScore() == 0 && getFirstRoll() != MINUS_ONE;
    }
    public boolean isCalculated() {
        return getBonusScore() == 0 && getFirstRoll() != MINUS_ONE;
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

    public int getThirdRoll() {
        return thirdRoll;
    }

    public void setThirdRoll(int thirdRoll) {
        this.thirdRoll = thirdRoll;
    }
}

