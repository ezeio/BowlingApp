package com.app.model;

public class GameScore {

    private Frame[] frames;

    public Frame getFrame(int frameIndex) {
        if( frameIndex >= frames.length || frameIndex < 0) {
            return null;
        }
        return frames[frameIndex];
    }
}
