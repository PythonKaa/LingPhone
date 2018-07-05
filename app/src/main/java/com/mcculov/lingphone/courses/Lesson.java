package com.mcculov.lingphone.courses;

import java.util.ArrayList;

public class Lesson {

    private String mediaFileName;
    private String srcName;
    private String nativeName;

    public ArrayList<LessonPhrase> getPhrases() {
        return phrases;
    }

    private ArrayList<LessonPhrase> phrases;

    public Lesson(String mediaFileName, String srcName, String nativeName) {
        this.srcName = srcName;
        this.nativeName = nativeName;
        this.mediaFileName = mediaFileName;
        phrases = new ArrayList<>();
    }

    public String getSrcName() {
        return srcName;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getMediaFileName() {
        return mediaFileName;
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

    public class LessonPhrase {
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
