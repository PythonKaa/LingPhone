package com.example.mcculov.lingphone;

public class PausePlayer extends AbstactPlayer {

    private int currentTime = 0;
    private boolean isPlaying = false;

    @Override
    protected void updateTask() {
        if (isPlaying()) {
            if (currentTime + DELTA >= endTime) {
                stopPlay();
                callOnComplete();
                currentTime = endTime;
            } else {
                currentTime = currentTime + DELTA;
            }
        }
    }

    @Override
    protected void stopPlay() {
        super.stopPlay();
        isPlaying = false;
    }

    @Override
    protected void startPlay() {
        super.startPlay();
        isPlaying = true;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public void play(int from, int to) {
        super.play(from, to);
        currentTime = startTime;
        startPlay();
    }

    @Override
    public int getCurrentPosition() {
        return currentTime;
    }

    @Override
    public int getDuration() {
        return endTime - startTime;
    }

    @Override
    public void seekTo(int msec) {
        currentTime = msec;
    }

}
