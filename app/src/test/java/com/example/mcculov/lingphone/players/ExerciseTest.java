package com.example.mcculov.lingphone.players;

import com.mcculov.lingphone.TestCourseLoader;
import com.mcculov.lingphone.courses.Course;
import com.mcculov.lingphone.courses.CourseDefinition;
import com.mcculov.lingphone.players.Exercise;
import com.mcculov.lingphone.players.ExerciseFabrique;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExerciseTest {

    private Exercise exercise;

    @Before
    public void SetUp() {
 //       CourseDefinition courseDefinition = TestCourseLoader.LoadCourseDef();
        Course course = courseDefinition.createCourse();
        exercise = ExerciseFabrique.createExercise(course.lessons.get(0), "NL");
    }

    @Test
    public void textIsEmpty() {
        assert(!exercise.textIsEmpty());
    }

    @Test
    public void getPhraseIndex() {
        assertEquals(0, exercise.getPhraseIndex(400));
        assertEquals(1, exercise.getPhraseIndex(1510));
        assertEquals(2, exercise.getPhraseIndex(4500));

        assertEquals(1, exercise.getPhraseIndex(1500));
    }

    @Test
    public void getPlayItem() {
        assertEquals(exercise.getPlayItems().get(0), exercise.getPlayItem(400));
        assertEquals(exercise.getPlayItems().get(1), exercise.getPlayItem(1510));
        assertEquals(exercise.getPlayItems().get(2), exercise.getPlayItem(4500));
    }


    @Test
    public void getLastPlayItem() {
        assertEquals(exercise.getPlayItems().get(12), exercise.getLastPlayItem());
    }


    @Test (expected = IllegalArgumentException.class)
    public void getNextPlayItem() {
        assertEquals(exercise.getPlayItems().get(1), exercise.getNextPlayItem(exercise.getPlayItems().get(0)));
        assertEquals(exercise.getPlayItems().get(2), exercise.getNextPlayItem(exercise.getPlayItems().get(1)));

        assertEquals(exercise.getPlayItems().get(0), exercise.getNextPlayItem(null));

        // exception expected
        exercise.getNextPlayItem(exercise.getLastPlayItem());

    }

    @Test
    public void getPosition() {
        Exercise.PlayItem pi = exercise.getPlayItems().get(1);
        assertEquals(4200, pi.getPosition(1700));
    }

    @Test (expected = IllegalArgumentException.class)
    public void getPositionExceptionBefore() {
        Exercise.PlayItem pi = exercise.getPlayItems().get(1);
        pi.getPosition(100);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getPositionExceptionAfter() {
        Exercise.PlayItem pi = exercise.getPlayItems().get(1);
        pi.getPosition(5800);
    }

}