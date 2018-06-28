package com.example.mcculov.lingphone;

import java.util.ArrayList;

public class CourseDefinition {

    public class DefsForLanguage {

        public ArrayList<String> phrases;
        public ArrayList<String> partNames;

        public DefsForLanguage() {
            phrases = new ArrayList<String>();
            partNames = new ArrayList<String>();
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

            splittingParts = new ArrayList<Integer>();
            splittingPhraseStarts = new ArrayList<Integer>();
            srcLang = new DefsForLanguage();
            nativeLang = new DefsForLanguage();
        }
    }

    public ArrayList<FileDefinition> mediaFiles;
    public String srcCourseName;
    public String nativeCourseName;

    public CourseDefinition() {
        mediaFiles = new ArrayList<FileDefinition>();
    }

    public CourseDefinition(String courseName) {
        mediaFiles = new ArrayList<FileDefinition>();
        srcCourseName = courseName;
    }

    public FileDefinition createFileDefinition(int fileID) {
        return new FileDefinition(fileID);
    }
}
