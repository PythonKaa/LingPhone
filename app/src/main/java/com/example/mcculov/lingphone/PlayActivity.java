package com.example.mcculov.lingphone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.play_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.play_item:
                Toast.makeText(this, R.string.play_menu_hint, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.pause_item:
                Toast.makeText(this, R.string.pause_menu_hint, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings_item:
                Toast.makeText(this, R.string.setting_menu_hint, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
