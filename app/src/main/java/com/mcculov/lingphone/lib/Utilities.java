package com.mcculov.lingphone.lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.widget.Toast;

import com.example.mcculov.lingphone.R;
import com.mcculov.lingphone.players.PlayersManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Utilities {
    /**
     * Function to convert milliseconds time to
     * Timer Format
     * Hours:Minutes:Seconds
     * */
    public static String milliSecondsToTimer(long milliseconds){
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int)( milliseconds / (1000*60*60));
        int minutes = (int)(milliseconds % (1000*60*60)) / (1000*60);
        int seconds = (int) ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
        // Add hours if there
        if(hours > 0){
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if(seconds < 10){
            secondsString = "0" + seconds;
        }else{
            secondsString = "" + seconds;}

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    /**
     * Function to get Progress percentage
     * @param currentDuration
     * @param totalDuration
     * */
    public static int getProgressPercentage(long currentDuration, long totalDuration){
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage =(((double)currentSeconds)/totalSeconds)*100;

        // return percentage
        return percentage.intValue();
    }

    /**
     * Function to change progress to timer
     * @param progress -
     * @param totalDuration
     * returns current duration in milliseconds
     * */
    public static int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double)progress) / 100) * totalDuration);

        // return current duration in milliseconds
        return currentDuration * 1000;
    }

    public static String getPathToData(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        return file.getPath();
    }

    public static String convertStreamToString(InputStream inputStream) {
        byte[] buffer = null;
        try {
            int size = inputStream.available();
            buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buffer);
    }

    public static String readFileFromInternal(Context context, String fileName) {

        //Get the text file
        File file = new File(getPathToData(context, fileName));

        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public static String readFileFromAssets(Context context, String fileName) {
            AssetManager am = context.getAssets();
            String s = "";
            try {
                InputStream is = am.open(fileName);
                s = convertStreamToString(is);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
    }

    public static void setMediaFor(Context context, String playerName, String fileName) {
        try {
            AssetFileDescriptor afd = context.getAssets().openFd(fileName);
            PlayersManager.getInstance().getPlayer(playerName).setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        }
        catch(IllegalArgumentException | IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }
}
