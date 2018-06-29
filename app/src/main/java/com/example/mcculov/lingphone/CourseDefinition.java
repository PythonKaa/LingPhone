package com.example.mcculov.lingphone;

import java.util.ArrayList;

public class CourseDefinition {

    public ArrayList<FileDefinition> mediaFiles;
    public String srcCourseName;
    public String nativeCourseName;

    public CourseDefinition() {
        mediaFiles = new ArrayList<>();
    }

    public CourseDefinition(String courseName) {
        this();
        srcCourseName = courseName;
    }

    public FileDefinition createFileDefinition(int fileID) {
        return new FileDefinition(fileID);
    }

    public class DefsForLanguage {

        public ArrayList<String> phrases;
        public ArrayList<String> partNames;

        public DefsForLanguage() {
            phrases = new ArrayList<>();
            partNames = new ArrayList<>();
        }
    }

    public class FileDefinition {
        public int mediaFileID;
        public ArrayList<Integer> splittingPhraseStarts;
        public ArrayList<Integer> splittingParts;
        public DefsForLanguage srcLang;
        public DefsForLanguage nativeLang;

        public FileDefinition(int mediaFileID) {
            this.mediaFileID = mediaFileID;

            splittingParts = new ArrayList<>();
            splittingPhraseStarts = new ArrayList<>();
            srcLang = new DefsForLanguage();
            nativeLang = new DefsForLanguage();
        }
    }

    public Course createCourse() {
        Course course = new Course(srcCourseName, nativeCourseName);

        for (FileDefinition mf: mediaFiles) {
                int currentPartIndex = 0;
                int currentPartEnd = 0;

            for (int i = 0; i < mf.splittingParts.size(); i++) {

                Lesson ls = new Lesson(mf.mediaFileID,
                        mf.srcLang.partNames.get(i),
                        mf.nativeLang.partNames.get(i));

                if (currentPartIndex < mf.splittingParts.size() - 1)
                    currentPartEnd = mf.splittingParts.get(i+1);
                else
                    currentPartEnd = mf.splittingPhraseStarts.size();

                for (int j = mf.splittingParts.get(i); j < currentPartEnd; j++) {
                    ls.addPhrase(
                            mf.srcLang.phrases.get(j),
                            mf.nativeLang.phrases.get(j),
                            mf.splittingPhraseStarts.get(j),
                            mf.splittingPhraseStarts.get(j+1));
                }
                course.lessons.add(ls);
            }
        }
        return course;
    }
}
