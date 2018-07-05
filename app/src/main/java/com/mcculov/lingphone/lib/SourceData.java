package com.mcculov.lingphone.lib;

import android.content.Context;

import com.mcculov.lingphone.courses.Course;
import com.mcculov.lingphone.courses.CourseDefinition;
import com.mcculov.lingphone.players.MPlayer;
import com.mcculov.lingphone.players.PausePlayer;
import com.mcculov.lingphone.players.PlayersManager;
import com.google.gson.Gson;


public class SourceData {
    private static final SourceData ourInstance = new SourceData();
    private Course course;
    Context context;

    private SourceData() {
//        CourseDefinition courseDefinition = TestCourseLoader.LoadCourseDef();

        MPlayer mpp = new MPlayer();
        PlayersManager.getInstance().registerPlayer("Media", mpp);

        PausePlayer pp = new PausePlayer();
        PlayersManager.getInstance().registerPlayer("Pause", pp);
    }

    public static SourceData getInstance() {
        return ourInstance;
    }

    public Course getCourse() {
        return course;
    }

    public void initializeWithContext(Context context) {


        this.context = context;
        String jsonText = Utilities.readFileFromAssets(context, "course.json");

        CourseDefinition courseDefinition = null;
        courseDefinition = new Gson().fromJson(jsonText, CourseDefinition.class);
        if (courseDefinition != null)
            course = courseDefinition.createCourse();
    }


    protected void finalize() {
        PlayersManager.getInstance().getPlayer("Media").release();
    }


}