package com.app.model;

public class TenPinGameScore implements GameScore{

    private Frame[] frames;
    private int totalScore;



    public TenPinGameScore(int numberOfFrames){
        setUpFrames(numberOfFrames);
    }

    private void setUpFrames(int numberOfFrames) {
        frames = new Frame[numberOfFrames];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = new Frame(this);
        }
    }

    @Override
    public void calculate(int frameNum, int pinsDown) {
        //frameNum = frameNum -1;
        if( frameNum < 0 || frames[frameNum].isCalculated(frameNum))return;

        setScore(frameNum, pinsDown);

        calculate(frameNum - 1, pinsDown);
    }

    private void setScore(int frameNum, int pinsDown) {
        Frame frame = frames[frameNum];
        frame.setScore(pinsDown);
        frame.updateTotalScore(pinsDown);

    }

    public Frame getFrame(int frame) {
        int frameIndex = frame - 1;
        if( frameIndex >= frames.length || frameIndex < 0) {
            return null;
        }
        return frames[frameIndex];
    }


    public boolean isNextPlayerTurn(int frameNum) {
        return frames[frameNum].isBowled();
    }

    @Override
    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
