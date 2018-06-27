package com.example.mcculov.lingphone;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

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

    protected void finalize() {
        mp.release();
    }

    public List<String> getText() {
        CourseDefinition cd = TestCourseLoader.LoadCourseDef();

        ArrayList<String> a = new ArrayList<String>();


        CourseDefinition.SplittingPhrase sp[] = new CourseDefinition.SplittingPhrase[cd.splittingPhrases.size()];
        cd.splittingPhrases.toArray(sp);

        String la[] = new String[cd.foreignLang.phrases.size()];
        la = cd.foreignLang.phrases.toArray(la);

        for (int i = 0; i < cd.splittingPhrases.size()-1; i++) {
           if (sp[i+1].resID == R.raw.intro_part_1)
               a.add(la[i]);
        }
        return a;
    }
}

