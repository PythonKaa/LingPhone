package com.example.mcculov.lingphone;

import java.util.ArrayList;

public class Lesson {

    private int mediaID;
    private String srcName;
    private String nativeName;
    private ArrayList<LessonPhrase> phrases;

    public Lesson(int mediaID, String srcName, String nativeName) {
        this.srcName = srcName;
        this.nativeName = nativeName;
        this.mediaID = mediaID;
        phrases = new ArrayList<>();
    }

    public String getSrcName() {
        return srcName;
    }

    public String getNativeName() {
        return nativeName;
    }

    public int getMediaID() {
        return mediaID;
    }

    public void addPhrase(String srcText, String nativeText, int from, int to) {
        phrases.add(new LessonPhrase(srcText, nativeText, from, to));
    }

    public ArrayList<String> getNativeText() {
        ArrayList<String> res = new ArrayList<>();
        for (LessonPhrase ph : phrases) {
            res.add(ph.nativeText);
        }
        return res;
    }

    public ArrayList<String> getSrcText() {
        ArrayList<String> res = new ArrayList<>();
        for (LessonPhrase ph : phrases) {
            res.add(ph.srcText);
        }
        return res;
    }

    public int getIndexOfPhrase(int msec) {
        for (int i = 0; i < phrases.size(); i++) {
            LessonPhrase phrase = phrases.get(i);
            if (msec >= phrase.from && msec < phrase.to)
                return i;
        }
        return -1;
    }

    private class LessonPhrase {
        public String srcText;
        public String nativeText;
        public int from;
        public int to;

        public LessonPhrase(String srcText, String nativeText, int from, int to) {
            this.srcText = srcText;
            this.nativeText = nativeText;
            this.from = from;
            this.to = to;
        }
    }

}
