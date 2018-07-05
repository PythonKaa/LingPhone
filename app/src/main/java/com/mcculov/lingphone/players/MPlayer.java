package com.mcculov.lingphone.players;

import android.media.MediaPlayer;

import java.io.FileDescriptor;
import java.io.IOException;

public class MPlayer extends AbstractPlayer implements IMPlayer, MediaPlayer.OnCompletionListener {

    private MediaPlayer mp;
    private MediaPlayer.OnCompletionListener listener;

    public MPlayer() {
        mp = new MediaPlayer();
        mp.setOnCompletionListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer arg0) {
        callOnCompletePlay();
    }

    @Override
    protected void stopPlay() {
        super.stopPlay();
        mp.pause();
    }

    @Override
    protected void startPlay() {
        super.startPlay();
        mp.start();
    }

    @Override
    public boolean isPlaying() {
        return mp.isPlaying();
    }

    @Override
    public void setDataSource(String fileName) throws IOException {
        if (mp.isPlaying())
            mp.reset();
        mp.setDataSource(fileName);
        mp.prepare();
        mp.setOnCompletionListener(this);
        setStartTime(0);
        setEndTime(mp.getDuration());
    }

    @Override
    public void setDataSource(FileDescriptor fd, long offset, long length) throws IOException {
        if (mp.isPlaying())
            mp.reset();
        mp.setDataSource(fd, offset, length);
        mp.prepare();
        mp.setOnCompletionListener(this);
        setStartTime(0);
        setEndTime(mp.getDuration());
    }

    @Override
    public int getCurrentPosition() {
        return mp.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        return mp.getDuration();
    }

    @Override
    public void seekTo(int msec) {
        mp.seekTo(msec);
    }

    @Override
    public void release() {
        super.release();
        mp.release();
    }

}
