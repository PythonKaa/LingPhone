package com.mcculov.lingphone.players;

import java.io.FileDescriptor;
import java.io.IOException;

import static java.lang.Math.min;

public class ExercisePlayer implements IMPlayer, IMPlayer.OnCompletePlayListener {


    private Exercise exercise;
    private OnPhraseChangeListener onPhraseChangeListener;
    private OnCompletePlayListener onCompletePlayListener;
    private int currentIndex = -1;
    private boolean isPlaying = false;
    private boolean stayOnPhraseStart = true;

    private Exercise.PlayItem currentPlayItem = null;
    private IMPlayer currentPlayer = null;


    public ExercisePlayer(Exercise exercise) {
        this.exercise = exercise;
    }

    private void callOnPhraseChange(int newLine) {
        if (onPhraseChangeListener != null)
            onPhraseChangeListener.onPhraseChanged(newLine);
    }

    private void callOnCompletePlay() {
        isPlaying = false;
        if (onCompletePlayListener != null)
            onCompletePlayListener.onCompletePlay();
    }

    public void setOnPhraseChangeListener(OnPhraseChangeListener listener) {
        onPhraseChangeListener = listener;
    }

    public void checkPhraseIndex(int msec) {
        int newIndex = exercise.getPhraseIndex(msec);
        if (newIndex != currentIndex) {
            currentIndex = newIndex;
            if (!exercise.textIsEmpty())
                callOnPhraseChange(newIndex);
        }
    }

    @Override
    public int getStartTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setStartTime(int startTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getEndTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setEndTime(int endTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setOnCompletePlayListener(OnCompletePlayListener listener) {
        onCompletePlayListener = listener;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public void pause() {
        isPlaying = false;
        currentPlayer.pause();
    }

    @Override
    public void start() {
        isPlaying = true;
        currentPlayer.start();
    }


    @Override
    public int getCurrentPosition() {
        int fromStart = currentPlayer.getCurrentPosition() - currentPlayItem.getPhraseFrom();
        int startPos = currentPlayItem.getTimeFinish() - currentPlayItem.getPhraseLen();
        int res = startPos + fromStart;
        return res;
    }

    @Override
    public int getDuration() {
        return exercise.getLastPlayItem().getTimeFinish();
    }

    private void switchToPlayItem(Exercise.PlayItem pItem, int msec) {

        if (isPlaying)
            currentPlayer.pause();

        if (currentPlayer == null || !pItem.getPlayerName().equals(currentPlayItem.getPlayerName())) {
            // у нас другой или новый плеер
            currentPlayer = PlayersManager.getInstance().getPlayer(pItem.getPlayerName());
            currentPlayer.setOnCompletePlayListener(this);
        }

        currentPlayer.setStartTime(pItem.getPhraseFrom());
        currentPlayer.setEndTime(pItem.getPhraseFrom() + pItem.getPhraseLen());
        currentPlayer.seekTo(pItem.getPosition(msec));
        currentPlayItem = pItem;
        if (isPlaying)
            currentPlayer.start();
    }

    @Override
    public void setDataSource(String fileName) throws IOException {
        if (currentPlayer != null)
            currentPlayer.setDataSource(fileName);
    }

    @Override
    public void setDataSource(FileDescriptor fd, long offset, long length) throws IOException  {
        if (currentPlayer != null)
            currentPlayer.setDataSource(fd, offset, length);
    }

    @Override

    public void seekTo(int msec) {

        msec = min(msec, getDuration() - 1);
        Exercise.PlayItem pItem = exercise.getPlayItem(msec);
        if (stayOnPhraseStart) {
            while (pItem.getIndexInPhrase() > 0)
                pItem = exercise.getPrevPlayItem(pItem);
            msec = pItem.getTimeFinish() - pItem.getPhraseLen();
        }
        if (pItem == currentPlayItem) {
            // ничего не поменялось, мы все там же
            currentPlayer.seekTo(pItem.getPosition(msec));
        } else {
            switchToPlayItem(pItem, msec);
        }
    }

    @Override
    public void release() {
        currentPlayer.release();
    }

    @Override
    public void onCompletePlay() {
        if (currentPlayItem == exercise.getLastPlayItem()) {
            callOnCompletePlay();
        } else {
            Exercise.PlayItem item = exercise.getNextPlayItem(currentPlayItem);
            stayOnPhraseStart = false;
            seekTo(item.getTimeFinish() - item.getPhraseLen());
            stayOnPhraseStart = true;
        }
    }

    public interface OnPhraseChangeListener {
        void onPhraseChanged(int newLine);
    }


}
