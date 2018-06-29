package com.example.mcculov.lingphone;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class PlayActivity extends LingActivity implements IMPlayer.OnCompletePlayListener, SeekBar.OnSeekBarChangeListener {

    private static final int seekForwardTime = 5000; // 5000 milliseconds
    private static final int seekBackwardTime = 5000; // 5000 milliseconds
    private ImageButton btnPlay;
    private SeekBar lessonProgressBar;
    private TextView lessonTitleLabel;
    private TextView lessonCurrentDurationLabel;
    private TextView lessonTotalDurationLabel;
    private PlayLessonManager plm;
    private MPlayer mp;
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();
    private Utilities utils;
    private PackerToBundle stateSaver;
    private ActivityState activityState;
    /**
     * Background Runnable thread
     */
    private Runnable mUpdateTimeTask = new Runnable() {

        public void run() {
            int totalDuration = mp.getDuration();
            int currentDuration = mp.getCurrentPosition();
            plm.setCurrentPosition(currentDuration);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // All player buttons
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);

        lessonProgressBar = (SeekBar) findViewById(R.id.lessonProgressBar);
        lessonTitleLabel = (TextView) findViewById(R.id.lessonTitle);
        lessonCurrentDurationLabel = (TextView) findViewById(R.id.lessonCurrentDurationLabel);
        lessonTotalDurationLabel = (TextView) findViewById(R.id.lessonTotalDurationLabel);

        // Mediaplayer
        plm = new PlayLessonManager();
        mp = new MPlayer();
        utils = new Utilities();
        stateSaver = new PackerToBundle();


        // Listeners
        lessonProgressBar.setOnSeekBarChangeListener(this);
        mp.setOnCompletePlayListener(this);

        if (savedInstanceState == null) {
            activityState = new ActivityState();
            activityState.lessonTitle = "Test lesson";
            activityState.playPosition = 0;
            activityState.isPlay = true;
            activityState.selectedLine = -1;
        } else {
            activityState = stateSaver.restoreFromBundle(savedInstanceState, ActivityState.class);
        }


        TableLayout textTable = (TableLayout) findViewById(R.id.TableLayout_Text);

        int index = 0;
        for (String s : plm.getText()) {
            addLessonLine(textTable, s, index++);
        }
        selectLine(activityState.selectedLine, true); // только после того, как создали все строки

        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setVisibility(View.INVISIBLE);

        // By default play first lesson
        playLesson();

        plm.setOnLineChangeListener(new PlayLessonManager.OnLineChangeListener() {
            @Override
            public void lineChanged(int newLine) {
                setSelectedLine(newLine);
            }
        });

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
                    mp.startPlay();
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
        ImageButton btnForward = (ImageButton) findViewById(R.id.btnForward);
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
        ImageButton btnBackward = (ImageButton) findViewById(R.id.btnBackward);
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

    private void addLessonLine(final TableLayout textTable, String value, int index) {

        final TableRow newRow = new TableRow(this);

        int textColor = Color.GRAY; // R.color.lesson_text_color;
        float textSize = getResources().getDimension(R.dimen.text_size);

        TextView textView = new TextView(this);

        textView.setMaxWidth(1);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        textView.setTextColor(textColor);
        textView.setText(value);
        textView.setTag("Line #" + index);

        newRow.addView(textView);
        textTable.addView(newRow);
    }

    private void setSelectedLine(int lineNo) {
        if (activityState.selectedLine != lineNo) {
            selectLine(activityState.selectedLine, false);
            selectLine(lineNo, true);

            activityState.selectedLine = lineNo;
        }
    }

    private void selectLine(int lineNo, boolean select) {
        if (lineNo != -1) {
            TableLayout tl = (TableLayout) findViewById(R.id.TableLayout_Text);
            TextView textView = (TextView) tl.findViewWithTag("Line #" + lineNo);

            if (select) {
                textView.setTextColor(Color.WHITE);
                ScrollView sv = (ScrollView) findViewById(R.id.ScrollView_TextDemo);
                TableRow tableRow = (TableRow) textView.getParent();
                int newPosition = tableRow.getTop() - (sv.getHeight() - (tableRow.getBottom() - tableRow.getTop())) / 2;
                sv.smoothScrollTo(0, newPosition);
            } else
                textView.setTextColor(Color.GRAY);
        }

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
            mp.startPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        activityState.playPosition = mp.getCurrentPosition();
        stateSaver.saveToBundle(outState, activityState);
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
    public void onCompletePlay() {
        // repeat is on play same lesson again
        activityState.playPosition = 0;
        playLesson();
    }

    protected void finalize() {
        mp.release();
    }

    private class ActivityState {
        public int playPosition;
        public String lessonTitle;
        public boolean isPlay;
        public int selectedLine;
    }


}
