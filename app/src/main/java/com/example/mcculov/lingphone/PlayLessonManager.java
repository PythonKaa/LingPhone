package com.example.mcculov.lingphone;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;


public class PlayLessonManager {

    interface OnLineChangeListener {
        void lineChanged(int newLine);
    }

    private MediaPlayer mp;
    MediaPlayer.OnCompletionListener cl;
    OnLineChangeListener onLineChanger;
    CourseDefinition courseDefinition;
    private int currentIndex = -1;

    private void onLineChangeCall(int newLine) {
        if (onLineChanger != null)
            onLineChanger.lineChanged(newLine);
    }

    public void setOnLineChangeListener(OnLineChangeListener l) {
        onLineChanger = l;
    }

    public PlayLessonManager() {
        this.mp = new MediaPlayer();
        courseDefinition = TestCourseLoader.LoadCourseDef();
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
        int currentPosition = mp.getCurrentPosition();
        checkCurrentPosition(currentPosition);
        return currentPosition;
    }

    private void checkCurrentPosition(int currentPosition) {
        int newIndex = getCurrentIndex(currentPosition);
        if (newIndex != currentIndex) {
            currentIndex = newIndex;
            onLineChangeCall(newIndex);
        }
    }

    private int getCurrentIndex(int position) {
        int count = courseDefinition.mediaFiles.get(0).splittingPhraseStarts.size() - 1;
        for (int i = 0; i < count; i++) {
            int lineStart = courseDefinition.mediaFiles.get(0).splittingPhraseStarts.get(i+1);
            if (position < lineStart)
                return i;
        }
        return 0;
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

    protected void finalize() {
        mp.release();
    }

    public List<String> getText() {

        return courseDefinition.mediaFiles.get(0).srcLang.phrases;
    }
}

