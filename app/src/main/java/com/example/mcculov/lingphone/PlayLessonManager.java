package com.example.mcculov.lingphone;

import android.content.Context;
import android.media.MediaPlayer;

public class PlayLessonManager {

    private MediaPlayer mp;
    MediaPlayer.OnCompletionListener cl;

    public PlayLessonManager() {
        this.mp = new MediaPlayer();
    }

    public void setResourceID(Context context, int resid) {
        mp.reset();
        mp = MediaPlayer.create(context, resid);
        mp.setOnCompletionListener(cl);
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener) {
        cl = listener;
        mp.setOnCompletionListener(listener);
    }

    public boolean isPlaying() {
        return mp.isPlaying();
    }

    public void pause() {
        mp.pause();
    }

    public void start() {
        mp.start();
    }

    public int getCurrentPosition() {
        return mp.getCurrentPosition();
    }

    public int getDuration() {
        return mp.getDuration();
    }

    public void seekTo(int msec) {
        mp.seekTo(msec);
    }

    public void release() {
        mp.release();
    }

    public void reset() {
        mp.reset();
    }
}

