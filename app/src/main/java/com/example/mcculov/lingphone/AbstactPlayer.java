package com.example.mcculov.lingphone;

import android.os.Handler;

class AbstactPlayer implements IMPlayer {

    protected static final int DELTA = 100;
    protected int startTime = 0;
    protected int endTime = 0;
    private Handler mHandler = new Handler();
    private OnCompletePlayListener onCompletePlayListener;
    private Runnable mUpdateTimeTask = new Runnable() {

        public void run() {
            updateTask();
            mHandler.postDelayed(this, DELTA);
        }
    };

    protected void updateTask() {

    }

    protected void stopPlay() {
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    protected void startPlay() {
        mHandler.postDelayed(mUpdateTimeTask, DELTA);
    }


    protected void callOnComplete() {
        if (onCompletePlayListener != null)
            onCompletePlayListener.onCompletePlay();
    }

    @Override
    public void setOnCompletePlayListener(OnCompletePlayListener listener) {
        onCompletePlayListener = listener;
    }

    @Override
    public boolean isPlaying() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void start() {
        startPlay();
    }

    @Override
    public void pause() {
        stopPlay();
    }

    @Override
    public void play(int from, int to) {
        if (to < from)
            throw new IllegalArgumentException("called play(from, to) => to < from");

        startTime = from;
        endTime = to;
    }

    @Override
    public int getCurrentPosition() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDuration() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void seekTo(int msec) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void release() {
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    @Override
    public void reset() {
        mHandler.removeCallbacks(mUpdateTimeTask);
    }
}
