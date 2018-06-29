package com.example.mcculov.lingphone;

public interface IMPlayer {

    void setOnCompletePlayListener(OnCompletePlayListener listener);

    boolean isPlaying();

    void pause();

    void start();

    void play(int from, int to);

    int getCurrentPosition();

    int getDuration();

    void seekTo(int msec);

    void release();

    void reset();

    public interface OnCompletePlayListener {
        void onCompletePlay();
    }
}
