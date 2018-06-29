package com.example.mcculov.lingphone;

import java.util.ArrayList;

public class Course {
    public String srcName;
    public String nativeName;
    public ArrayList<Lesson> lessons;

    public Course(String srcName, String nativeName) {
        this.srcName = srcName;
        this.nativeName = nativeName;
        lessons = new ArrayList<>();
    }

}
