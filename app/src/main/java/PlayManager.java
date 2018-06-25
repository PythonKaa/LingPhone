import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

public class PlayManager {

    private static final String MEDIA_TIME = "MEDIA_TIME";
    private int mediaTime;
    private MediaPlayer mediaPlayer;

    public void OnCreateActivity(Bundle savedInstanceState) {
        if (savedInstanceState != null) // Activity starting
        {
            // get the play position that was saved when config changed
            mediaTime = savedInstanceState.getInt(MEDIA_TIME);

        }

        mediaPlayer = new MediaPlayer();
        //mediaPlayer.setDataSource(this, Uri.parse(slideshow.getMusicPath()));
        //mediaPlayer.prepare(); // prepare the MediaPlayer to play
        mediaPlayer.setLooping(true); // loop the music
        mediaPlayer.seekTo(mediaTime); // seek to mediaTime
    }

    public void Play()
    {

    }

    public void Pause()
    {

    }

    public void OnSaveInstanceStateActivity(Bundle outState)
    {
        outState.putInt(MEDIA_TIME, mediaPlayer.getCurrentPosition());
    }

    public void OnDestroyActivity()
    {
        mediaPlayer.release();
    }

    public void OnResumeActivity()
    {
        mediaPlayer.start();
    }

    public void OnPauseActivity()
    {
        if (mediaPlayer.isPlaying() )
            mediaPlayer.pause();
    }
}
