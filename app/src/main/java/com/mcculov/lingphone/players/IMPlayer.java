package com.mcculov.lingphone.players;

import java.io.FileDescriptor;
import java.io.IOException;

public interface IMPlayer {

//    void setResourceID(Context context, int resid);

    void setDataSource(String fileName) throws IOException;

    void setDataSource(FileDescriptor fd, long offset, long length) throws IOException ;

    int getStartTime();

    void setStartTime(int startTime);

    int getEndTime();

    void setEndTime(int endTime);

    void setOnCompletePlayListener(OnCompletePlayListener listener);

    boolean isPlaying();

    void pause();

    void start();

//    void play(int from, int to);

    int getCurrentPosition();

    int getDuration();

    void seekTo(int msec);

    void release();

    interface OnCompletePlayListener {
        void onCompletePlay();
    }
}
