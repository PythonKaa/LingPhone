package com.example.mcculov.lingphone.players;

import com.example.mcculov.lingphone.R;
import com.mcculov.lingphone.TestCourseLoader;
import com.mcculov.lingphone.courses.Course;
import com.mcculov.lingphone.courses.CourseDefinition;
import com.mcculov.lingphone.courses.Lesson;
import com.mcculov.lingphone.players.Exercise;
import com.mcculov.lingphone.players.ExerciseFabrique;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExerciseFabriqueTest {

    private Course course;

    @Before
    public void setUp() throws Exception { CourseDefinition courseDefinition = TestCourseLoader.LoadCourseDef();
        course = courseDefinition.createCourse();
    }

    @Test
    public void createExercise() {
        Exercise exercise = ExerciseFabrique.createExercise(course.lessons.get(0), "NL");

        assertEquals(R.raw.intro_part_1, exercise.getMediaID());
        assertEquals(13, exercise.getText().size());
        assertEquals(13, exercise.getPlayItems().size());

        assertEquals(2500, exercise.getPlayItems().get(0).getPhraseFrom());
        assertEquals(1500, exercise.getPlayItems().get(0).getTimeFinish());
        assertEquals(1500, exercise.getPlayItems().get(0).getPhraseLen());

        assertEquals(4000, exercise.getPlayItems().get(1).getPhraseFrom());
        assertEquals(3200, exercise.getPlayItems().get(1).getTimeFinish());
        assertEquals(1700, exercise.getPlayItems().get(1).getPhraseLen());

        assertEquals(5700, exercise.getPlayItems().get(2).getPhraseFrom());
        assertEquals(4800, exercise.getPlayItems().get(2).getTimeFinish());
        assertEquals(1600, exercise.getPlayItems().get(2).getPhraseLen());
    }

    @Test
    public void simpleCreateExercise() {
        Lesson l = new Lesson(0, "Src", "Native");

        l.addPhrase("src1", "nat1", 0, 100);
        l.addPhrase("src2", "nat2", 100, 200);
        l.addPhrase("src3", "nat3", 200, 300);
        l.addPhrase("src4", "nat4", 300, 400);

        Exercise e = ExerciseFabrique.createExercise(l, "L");
        assertEquals(4, e.getPlayItems().size());
        assertEquals("Media", e.getPlayItems().get(0).getPlayerName());
        assertEquals(true, e.textIsEmpty());
        assertEquals(100, e.getPlayItems().get(1).getPhraseFrom());
        assertEquals(1, e.getPlayItems().get(1).getPhraseIndex());

        e = ExerciseFabrique.createExercise(l, "NL");
        assertEquals("Media", e.getPlayItems().get(0).getPlayerName());
        assertEquals(false, e.textIsEmpty());
        assertEquals(4, e.getText().size());

        e = ExerciseFabrique.createExercise(l, "P");
        assertEquals(4, e.getPlayItems().size());
        assertEquals("Pause", e.getPlayItems().get(0).getPlayerName());
        assertEquals(true, e.textIsEmpty());

        e = ExerciseFabrique.createExercise(l, "NPL");
        assertEquals(8, e.getPlayItems().size());
        assertEquals("Pause", e.getPlayItems().get(0).getPlayerName());
        assertEquals("Media", e.getPlayItems().get(1).getPlayerName());
        assertEquals(false, e.textIsEmpty());
        assertEquals(100, e.getPlayItems().get(2).getPhraseFrom());
        assertEquals(1, e.getPlayItems().get(2).getPhraseIndex());
        assertEquals(0, e.getPlayItems().get(2).getIndexInPhrase());
        assertEquals(100, e.getPlayItems().get(3).getPhraseFrom());
        assertEquals(1, e.getPlayItems().get(3).getPhraseIndex());
        assertEquals(1, e.getPlayItems().get(3).getIndexInPhrase());

    }
}