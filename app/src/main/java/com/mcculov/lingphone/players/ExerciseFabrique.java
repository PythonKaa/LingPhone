package com.mcculov.lingphone.players;

import com.mcculov.lingphone.courses.Lesson;

public class ExerciseFabrique {

    public static final char SRC_LANG = 'S';
    public static final char NATIVE_LANG = 'N';

    public static final char SIMPLE_LISTEN = 'L';
    public static final char PAUSE = 'P';
    public static final char RECORD = 'R';
    public static final char LISTEN_RECORDED = 'C';

    public static Exercise createExercise(Lesson lesson, String description) {
        Exercise res = new Exercise(lesson.getNativeName(), lesson.getMediaFileName());

        int msec = 0;
        int phraseIndex=0;
        int indexInPhrase;
        for (Lesson.LessonPhrase phrase : lesson.getPhrases()) {
            indexInPhrase = 0;
            for (int j = 0; j < description.length(); j++) {
                char ch = description.charAt(j);

                if (ch == NATIVE_LANG)
                    res.addPhrase(phrase.nativeText);
                if (ch == SRC_LANG)
                    res.addPhrase(phrase.srcText);
                if (ch == SIMPLE_LISTEN) {
                    msec = msec + (phrase.to - phrase.from);
                    res.addPlayItem("Media", phraseIndex, phrase.from, phrase.to - phrase.from, msec, indexInPhrase++);
                }
                if (ch == PAUSE) {
                    msec = msec + (phrase.to - phrase.from + 2);
                    res.addPlayItem("Pause", phraseIndex, phrase.from, phrase.to - phrase.from, msec, indexInPhrase++);
                }
            }
            phraseIndex++;
        }//
        return res;
    }
}

