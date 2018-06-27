package com.example.mcculov.lingphone;

import java.util.ArrayList;

public class CourseDefinition {

    public CourseDefinition() {
        mediaFileIDs = new ArrayList<Integer>();
        splittingPhrases = new ArrayList<SplittingPhrase>();
        splittingParts = new ArrayList<Integer>();
        foreignLang = new DefsForLanguage();
        nativeLang = new DefsForLanguage();
    }

    public class SplittingPhrase {
        public int resID;
        public int start;

        public SplittingPhrase(int resID, int start) {
            this.resID = resID;
            this.start = start;
        }
    }

    public class DefsForLanguage {

        public DefsForLanguage() {
            phrases = new ArrayList<String>();
            partNames = new ArrayList<String>();
        }

        public String CourseName;
        public ArrayList<String> phrases;
        public ArrayList<String> partNames;
    }

    public ArrayList<Integer> mediaFileIDs;
    public ArrayList<SplittingPhrase> splittingPhrases;
    public ArrayList<Integer> splittingParts;
    public DefsForLanguage foreignLang;
    public DefsForLanguage nativeLang;

    public void addSplittingPhrase(int resID, int start) {
        splittingPhrases.add(new CourseDefinition.SplittingPhrase(resID, start));
    }
}
