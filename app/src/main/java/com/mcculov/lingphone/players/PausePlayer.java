package com.mcculov.lingphone.players;

public class PausePlayer extends AbstractPlayer {

    private int currentTime = 0;
    private boolean isPlaying = false;

    @Override
    protected void updateTask() {
        currentTime = currentTime + DELTA;
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
    public int getCurrentPosition() {
        return currentTime;
    }

    @Override
    public int getDuration() {
        return getEndTime() - getStartTime();
    }

    @Override
    public void seekTo(int msec) {
        if (msec >= getEndTime())
            msec = getEndTime() - 1;
        if (msec < getStartTime())
            msec = getStartTime();
        currentTime = msec;
    }

}
