package com.example.mcculov.lingphone;

import android.content.Context;
import android.media.MediaPlayer;

public class MPlayer extends AbstactPlayer implements IMPlayer, MediaPlayer.OnCompletionListener {

    private MediaPlayer mp;
    private MediaPlayer.OnCompletionListener listener;

    public MPlayer() {
        mp = new MediaPlayer();
        mp.setOnCompletionListener(this);
    }

    public void setResourceID(Context context, int resid) {
        mp.reset();
        mp = MediaPlayer.create(context, resid);
        mp.setOnCompletionListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer arg0) {
        callOnComplete();
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
    public void play(int from, int to) {
        super.play(from, to);
        mp.seekTo(from);
        startPlay();
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

    @Override
    public void reset() {
        super.reset();
        mp.reset();
    }
}
