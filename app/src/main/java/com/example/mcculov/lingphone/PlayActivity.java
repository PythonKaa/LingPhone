package com.example.mcculov.lingphone;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;


public class PlayActivity extends LingActivity implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    private static final String MEDIA_TIME = "MEDIA_TIME";
    private static final String LESSON_TITLE = "LESSON_TITLE";
    private static final String IS_PLAY = "IS_PLAY";
    private static final String ACTIVITY_STATE = "ACTIVITY_STATE";

    private static final int seekForwardTime = 5000; // 5000 milliseconds
    private static final int seekBackwardTime = 5000; // 5000 milliseconds

//    private int playPosition = 0;
//    private String lessonTitle;
//    private boolean isPlay;

    private ImageButton btnPlay;
    private ImageButton btnForward;
    private ImageButton btnBackward;
    private SeekBar lessonProgressBar;
    private TextView lessonTitleLabel;
    private TextView lessonCurrentDurationLabel;
    private TextView lessonTotalDurationLabel;
    private PlayLessonManager mp;
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();
    private Utilities utils;

    private class ActivityState extends SavedState{
        public int playPosition;
        public String lessonTitle;
        public boolean isPlay;
    }

    private ActivityState activityState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // All player buttons
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnForward = (ImageButton) findViewById(R.id.btnForward);
        btnBackward = (ImageButton) findViewById(R.id.btnBackward);
        lessonProgressBar = (SeekBar) findViewById(R.id.lessonProgressBar);
        lessonTitleLabel = (TextView) findViewById(R.id.lessonTitle);
        lessonCurrentDurationLabel = (TextView) findViewById(R.id.lessonCurrentDurationLabel);
        lessonTotalDurationLabel = (TextView) findViewById(R.id.lessonTotalDurationLabel);

        // Mediaplayer
        mp = new PlayLessonManager();
        utils = new Utilities();

        // Listeners
        lessonProgressBar.setOnSeekBarChangeListener(this);
        mp.setOnCompletionListener(this);

        if (savedInstanceState == null) {
            activityState = new ActivityState();
            activityState.lessonTitle = "Test lesson";
            activityState.playPosition = 0;
            activityState.isPlay = true;
        } else {
            activityState.restoreFromBundle(savedInstanceState);
        }

        // By default play first lesson
        playLesson();

        /**
         * Play button click event
         * plays a lesson and changes button to pause image
         * pauses a lesson and changes button to play image
         * */
        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // check for already playing
                if (mp.isPlaying()) {
                        mp.pause();
                        activityState.isPlay = false;
                        // Changing button image to play button
                        btnPlay.setImageResource(R.drawable.btn_play);
                } else {
                    // Resume lesson
                        mp.start();
                        activityState.isPlay = true;
                        // Changing button image to pause button
                        btnPlay.setImageResource(R.drawable.btn_pause);
                }

            }
        });

        /**
         * Forward button click event
         * Forwards lesson specified seconds
         * */
        btnForward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // get current lesson position				
                int currentPosition = mp.getCurrentPosition();
                // check if seekForward time is lesser than lesson duration
                if (currentPosition + seekForwardTime <= mp.getDuration()) {
                    // forward lesson
                    mp.seekTo(currentPosition + seekForwardTime);
                } else {
                    // forward to end position
                    mp.seekTo(mp.getDuration());
                }
            }
        });

        /**
         * Backward button click event
         * Backward lesson to specified seconds
         * */
        btnBackward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // get current lesson position				
                int currentPosition = mp.getCurrentPosition();
                // check if seekBackward time is greater than 0 sec
                if (currentPosition - seekBackwardTime >= 0) {
                    // forward lesson
                    mp.seekTo(currentPosition - seekBackwardTime);
                } else {
                    // backward to starting position
                    mp.seekTo(0);
                }

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mHandler.post(mUpdateTimeTask);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (activityState.isPlay)
            mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (activityState.isPlay)
            mp.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        activityState.saveToBundle(outState);
    }

    public void playLesson() {
        // Play lesson
        try {
            mp.setResourceID(getApplicationContext(), R.raw.intro_part_1);
            mp.seekTo(activityState.playPosition);
            if (activityState.isPlay) {
                mp.start();
                btnPlay.setImageResource(R.drawable.btn_pause);
            } else {
                btnPlay.setImageResource(R.drawable.btn_play);
            }

            // Displaying lesson title
            lessonTitleLabel.setText(activityState.lessonTitle);

            // set Progress bar values
            //TODO: здесь будет дергаться, нужно рефакторить
            lessonProgressBar.setProgress(0);
            lessonProgressBar.setMax(100);

            // Updating progress bar
            updateProgressBar();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    /**
     * Update timer on seekbar
     */
    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    /**
     * Background Runnable thread
     */
    private Runnable mUpdateTimeTask = new Runnable() {

        public void run() {
            long totalDuration = mp.getDuration();
            long currentDuration = mp.getCurrentPosition();

            // Displaying Total Duration time
            lessonTotalDurationLabel.setText("" + utils.milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
            lessonCurrentDurationLabel.setText("" + utils.milliSecondsToTimer(currentDuration));

            // Updating progress bar
            int progress = (int) (utils.getProgressPercentage(currentDuration, totalDuration));
            //Log.d("Progress", ""+progress);
            lessonProgressBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };

    /**
     *
     * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }

    /**
     * When user starts moving the progress handler
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // remove message Handler from updating progress bar
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    /**
     * When user stops moving the progress hanlder
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mp.getDuration();
        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mp.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();
    }

    /**
     * On lesson Playing completed
     * if repeat is ON play same lesson again
     * if shuffle is ON play random lesson
     */
    @Override
    public void onCompletion(MediaPlayer arg0) {

        // repeat is on play same lesson again
        playLesson();
    }


}
