package com.mcculov.lingphone.players;

import android.os.Handler;

import java.io.FileDescriptor;
import java.io.IOException;

public class AbstractPlayer implements IMPlayer {

    protected static final int DELTA = 100;
    private int startTime = 0;
    private int endTime = 0;
    private Handler mHandler = new Handler();
    private OnCompletePlayListener onCompletePlayListener;
    private Runnable mUpdateTimeTask = new Runnable() {

        public void run() {
            updateTask();
            if (getCurrentPosition() > endTime) {
                stopPlay();
                callOnCompletePlay();
            }
            else
               mHandler.postDelayed(this, DELTA);
        }
    };

    protected void updateTask() { }

    protected void stopPlay() {
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    protected void startPlay() {
        mHandler.postDelayed(mUpdateTimeTask, DELTA);
    }

    @Override
    public int getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    @Override
    public int getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    protected void callOnCompletePlay() {
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
    public void setDataSource(String fileName)  throws IOException {}

    @Override
    public void setDataSource(FileDescriptor fd, long offset, long length) throws IOException {}

    @Override
    public void release() {
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

}
