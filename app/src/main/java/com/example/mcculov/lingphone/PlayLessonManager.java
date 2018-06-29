package com.example.mcculov.lingphone;

import java.util.List;


public class PlayLessonManager {

    OnLineChangeListener onLineChanger;
    CourseDefinition courseDefinition;
    private int currentIndex = -1;

    public PlayLessonManager() {
        courseDefinition = TestCourseLoader.LoadCourseDef();
    }

    private void onLineChangeCall(int newLine) {
        if (onLineChanger != null)
            onLineChanger.lineChanged(newLine);
    }

    public void setOnLineChangeListener(OnLineChangeListener l) {
        onLineChanger = l;
    }

    public int setCurrentPosition(int msec) {
        int currentPosition = msec;
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
            int lineStart = courseDefinition.mediaFiles.get(0).splittingPhraseStarts.get(i + 1);
            if (position < lineStart)
                return i;
        }
        return 0;
    }

    public List<String> getText() {
        return courseDefinition.mediaFiles.get(0).nativeLang.phrases;
    }

    interface OnLineChangeListener {
        void lineChanged(int newLine);
    }
}

